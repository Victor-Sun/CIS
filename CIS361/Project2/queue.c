#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>
#include "queue.h"


void init_queue(Queue* q) {
    q->head = NULL;
    q->tail = NULL;
    q->size = 0;
}


void enqueue(Queue* q, void* payload) {
    Node* node;

    node = malloc(sizeof(Node));
    if (node == NULL) {
        printf("Cannot malloc Node.\n");
        exit(1);
    }

    node->payload = payload;
    node->next = NULL;

    if (q->head == NULL) {
        q->head = node;
    }
    if (q->tail != NULL) {
        q->tail->next = node;
    }

    q->tail = node;
    q->size++;
}


void* dequeue(Queue* q) {
    Node* node;
    void* payload;

    node = q->head;
    if (node == NULL) {
        return NULL;
    } else {
        q->head = node->next;
        if (q->head == NULL) {
            q->tail = NULL;
        }
        payload = node->payload;
        free(node);
        q->size--;
        return payload;
    }
}
