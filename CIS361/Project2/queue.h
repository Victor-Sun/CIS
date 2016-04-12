typedef struct Node {
    void* payload;
    struct Node* next;
} Node;


typedef struct Queue {
    Node* head;
    Node* tail;
    int size;
} Queue;


void enqueue(Queue* q, void* payload);

void* dequeue(Queue* q);

void init_queue(Queue* q);
