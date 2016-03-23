// Project: Project 2 Event-Driven Simulation and Bash Script
// Name: Victor Sun
//
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
//#include "queue.h"

#define AVG_SERVICE 2.0
#define MAX_CUSTOMER_PERCENTAGE_SIZE 102


// Other steps:
// * Implement queue (as linked list).
// * Read the proj2.dat.
// * Implement the how_many_customers_at_this_minute()
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

int main () {
    //double t;
    int i;

    srand((unsigned) time(NULL));
    read_table_from_file("Proj2.dat");


    for(i = 0; i < 1000000; i++) {
        printf("%d\n", arrivingCustomers());
    }

    //t = expdist (AVG_SERVICE);

    return 0;
}
