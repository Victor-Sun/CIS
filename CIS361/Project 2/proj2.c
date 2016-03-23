#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "queue.h"

#define AVG_SERVICE 2.0
#define MAX_CUSTOMER_PERCENTAGE_SIZE 102


// Other steps:
// * DONE Implement queue (as linked list).
// * DONE Read the proj2.dat.
// * DONE Implement the how_many_customers_at_this_minute()
// * Some other stuff...
//
// For each minute from 0 to 480:
// 1. Check if any customer has finished service at any teller.
//    * If yes, the customer leaves and the teller is freed.
// 2. Roll a die to decide how many customers enter the bank.
//    * Put all new customers into the queue.
// 3. Check if any teller is free.
//    * If free, grab a customer from the queue.
//    * Roll a die to decide how long the customer will stay at service.


typedef struct CustomerPercentage {
    int customers;   // The amount of customers.
    int percentage;  // Percentage of time it happens.
} CustomerPercentage;

CustomerPercentage percentage_table[MAX_CUSTOMER_PERCENTAGE_SIZE];


void read_table_from_file(const char* filename) {
    FILE * fin;
    int x, y;
    int i;
    int sum;

    fin = fopen(filename,"r");
    if(fin == NULL){
        printf("File cannot be opened.\n");
        exit(1);
    }

    sum = 0;
    for (i = 0; i < MAX_CUSTOMER_PERCENTAGE_SIZE; i++) {
        if (fscanf(fin, "%d %d", &x, &y) != 2) {
            break;
        }
        percentage_table[i].customers = x;
        percentage_table[i].percentage = y;
        sum += y;
    }

    // Marking the end of the table.
    percentage_table[i].customers = -1;
    percentage_table[i].percentage = -1;

    if (sum != 100) {
        printf("Invalid data, the percentages do not sum up to 100.\n");
        exit(1);
    }

    fclose(fin);
}


double expdist (double mean) {
    double r = rand();
    r /= RAND_MAX;
    return -mean * log(r);
}


int arrivingCustomers() {
// Sum: 100
// 0     15       35        60
// |------|--------|---------|
//   0 15%   1 20%    2 25%
//
// 0    15  15
// 1    20  15+20 = 35
// 2    25  35+25 = 60
// 3    10  60+10 = 70
// 4    30  70+30 = 100

    int i;
    int range = (rand() % 100) + 1;
    //int range = (100 * (double) rand() / RAND_MAX) + 1;
    int sum = 0;

    for(i = 0; i < MAX_CUSTOMER_PERCENTAGE_SIZE; i++){
        if (percentage_table[i].customers < 0) {
            break;
        }
        sum += percentage_table[i].percentage;
        if(range <= sum){
            return percentage_table[i].customers;
        }
    }

    return range;
}


typedef struct Customer {
    int enter_time;   // When it enters the bank and enters the queue.
    int teller_time;  // When it goes to the teller.
    int exit_time;    // When it leaves the teller and leaves the bank.
} Customer;

typedef struct Teller {
    Customer* customer;
} Teller;


void simulation(int numOfTellers) {
    Teller* tellers;
    Queue q;
    int i, now;

    tellers = malloc(numOfTellers * sizeof(Teller));
    if (tellers == NULL) {
        printf("Cannot malloc tellers.\n");
        exit(1);
    }
    for (i = 0; i < numOfTellers; i++) {
        tellers[i].customer = NULL;
    }

    init_queue(&q);

    for (now = 0; now < 480; now++) {
        int new_customers;

        for (i = 0; i < numOfTellers; i++) {
            Customer* c = tellers[i].customer;
            if (c != NULL && c->exit_time >= now) {
                tellers[i].customer = NULL;
                printf("%d: Customer %p leaving teller %d: %d, %d, %d\n", now, c, i, c->enter_time, c->teller_time, c->exit_time);  // VERBOSE
                free(c);
            }
        }

        new_customers = arrivingCustomers();
        for (i = 0; i < new_customers; i++) {
            Customer* c;
            c = malloc(sizeof(Customer));
            if (c == NULL) {
                printf("Cannot malloc Customer.\n");
                exit(1);
            }
            c->enter_time = now;
            c->teller_time = -1;
            c->exit_time = -1;

            printf("%d: Customer %p entering the queue %d: %d\n", now, c, i, c->enter_time);  // VERBOSE
            enqueue(&q, c);
        }

        for (i = 0; i < numOfTellers; i++) {
            if (tellers[i].customer == NULL) {
                Customer* c;
                c = dequeue(&q);
                if (c == NULL) {
                    break;
                } else {
                    int service_time;

                    tellers[i].customer = c;
                    service_time = round(expdist(AVG_SERVICE));
                    c->teller_time = now;
                    c->exit_time = now + service_time;
                    printf("%d: Customer %p going to teller %d: %d, %d, %d\n", now, c, i, c->enter_time, c->teller_time, c->exit_time);  // VERBOSE
                }
            }
        }
    }

    // TODO: finish the remaining customers.

    free(tellers);
}


int main () {
    int i;

    srand((unsigned) time(NULL));
    read_table_from_file("Proj2.dat");

    for (i = 4; i <= 7; i++) {
        simulation(i);
    }

    return 0;
}
