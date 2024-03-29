// Project: Event-Drive Siumlation and Bash Script
// Name: Victor Sun
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include "queue.h"
#include "stats.h"

#define AVG_SERVICE 2.0
#define MAX_CUSTOMER_PERCENTAGE_SIZE 102

/*
	Structure for the amount of customers and the percentage of the amount showing
*/
typedef struct CustomerPercentage {
	int customers;   // The amount of customers.
	int percentage;  // Percentage of time it happens.
} CustomerPercentage;

CustomerPercentage percentage_table[MAX_CUSTOMER_PERCENTAGE_SIZE];
/*
	Read file from local directory
	@param filename: The file with the data on customer
*/
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

/*
	Get's a random time for how long a customer stays at a teller
	@param mean: The number
*/
double expdist (double mean) {
	double r = rand();
	r /= RAND_MAX;
	return -mean * log(r);
}

int arrivingCustomers() {

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

typedef struct Customer {
	int enter_time;   // When customer enters the bank and enters the queue.
	int teller_time;  // When customer goes to the teller.
	int exit_time;    // When customer leaves the teller and leaves the bank.
} Customer;

typedef struct Teller {
	Customer* customer;
} Teller;


void print_queue(Queue* q) {
	const Node* node;
	int count = 0;

	node = q->head;
	while (node) {
		printf("q[%d] -> %p payload=%p next=%p\n", count, node, node->payload, node->next);
		node = node->next;
		count++;
	}
}

void print_tellers(Teller* tellers, int n) {
	int i;
	for (i = 0; i < n; i++) {
		Customer* c;
		c = tellers[i].customer;
		if (c == NULL) {
			printf("t[%d] -> NULL\n", i);
		} else {
			printf("t[%d] -> %p: %d, %d, %d\n", i, c, c->enter_time, c->teller_time, c->exit_time);
		}
	}
}


void simulation(int numOfTellers) {
	Teller* tellers;
	Queue q;
	int i, now;
	const int max_time = 480;
	int remaining_customers;

	StatsCounter total_customers;
	StatsCounter time_in_queue;
	StatsCounter queue_length;

	init_statscounter(&total_customers);
	init_statscounter(&time_in_queue);
	init_statscounter(&queue_length);

	// Creating the tellers.
	tellers = malloc(numOfTellers * sizeof(Teller));
	if (tellers == NULL) {
		printf("Cannot malloc tellers.\n");
		exit(1);
	}
	for (i = 0; i < numOfTellers; i++) {
		tellers[i].customer = NULL;
	}


	init_queue(&q);

	// Main simulation loop.
	remaining_customers = 0;
	for (now = 0; now < max_time || remaining_customers > 0; now++) {
		//print_tellers(tellers, numOfTellers);

		remaining_customers = 0;
		for (i = 0; i < numOfTellers; i++) {
			Customer* c = tellers[i].customer;
			if (c != NULL) {
				if (c->exit_time <= now) {
					tellers[i].customer = NULL;
					//printf("%d: Customer %p leaving teller %d: %d, %d, %d\n", now, c, i, c->enter_time, c->teller_time, c->exit_time);  // VERBOSE
					free(c);
				} else {
					remaining_customers++;
				}
			}
		}

		//print_queue(&q);
		//print_tellers(tellers, numOfTellers);

		if (now < max_time) {  // Bank doors are still open.
			int new_customers;
			new_customers = arrivingCustomers();
			add_sample_statscounter(&total_customers, new_customers);
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
	
				//printf("%d: Customer %p entering the queue %d: %d\n", now, c, i, c->enter_time);  // VERBOSE
				enqueue(&q, c);
				remaining_customers++;
			}
		}

		//print_queue(&q);
		//print_tellers(tellers, numOfTellers);

		for (i = 0; i < numOfTellers; i++) {
			if (tellers[i].customer == NULL) {
				Customer* c;
				c = dequeue(&q);
				if (c == NULL) {
					break;
				} else {
					int service_time;

					service_time = round(expdist(AVG_SERVICE));
					c->teller_time = now;
					c->exit_time = now + service_time;
					
					add_sample_statscounter(&time_in_queue, now - c->enter_time);
					
					if (service_time == 0) {
						//printf("%d: Customer %p going to teller and leaving %d: %d, %d, %d\n", now, c, i, c->enter_time, c->teller_time, c->exit_time);  // VERBOSE
						free(c);
						i--;
					} else {
						remaining_customers++;
	
						tellers[i].customer = c;
						//printf("%d: Customer %p going to teller %d: %d, %d, %d\n", now, c, i, c->enter_time, c->teller_time, c->exit_time);  // VERBOSE
					}
				}
			}
		}
		add_sample_statscounter(&queue_length, q.size);
		//print_queue(&q);
		//print_tellers(tellers, numOfTellers);
	}

	free(tellers);
	
	printf("Total customers served: %d\n", (int) get_sum_statscounter(&total_customers));
	printf("Time waiting in line: avg=%f, max=%d\n", get_avg_statscounter(&time_in_queue), (int) get_max_statscounter(&time_in_queue));
	printf("Line length: avg=%f, max=%d\n", get_avg_statscounter(&queue_length), (int) get_max_statscounter(&queue_length));
}


int main () {
	int i;

	srand((unsigned) time(NULL));
	read_table_from_file("Proj2.dat");

	for (i = 4; i <= 7; i++) {
		printf("Simulation for %d tellers:\n", i);
		simulation(i);
		printf("\n");
	}

	return 0;
}
