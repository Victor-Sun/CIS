// Project: Project 2 Event-Driven Simulation and Bash Script
// Name: Victor Sun
//

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "queue.h"
#include "bool.h"
#define AVG_SERVICE 2.0

//Time it takes for a teller to help a customer
Rand double expdist (double mean) {
    double r = rand();
    r /= RAND_MAX;
    return -mean * log(r);
}

init_queue(queue *q){
    q->first = 0;
    q->last = QUEUESIZE-1;
    q->count = 0;
}

enqueue(queue *q, int x){
    if (q->count >= QUEUESIZE){
        printf("Warning: queue overflow enqueue x=%d\n",x);
    }
    else {
        q->last = (q->last+1) % QUEUESIZE;
        q->q[ q->last ] = x;
        q->count = q->count + 1;
    }
}

int dequeue(queue *q){
    int x;

    if (q->count <= 0) printf("Warning: empty queue dequeue.\n");
    else {
        x = q->q[ q->first ];
        q->first = (q->first+1) % QUEUESIZE;
        q->count = q->count - 1;
    }

    return(x);
}

int main () {
    double t;
    int range = rand() % 101;
    time_t t;
    FILE * fin;

    srand((unsigned) time(&t));
    t = expdist (AVG_SERVICE);

}

