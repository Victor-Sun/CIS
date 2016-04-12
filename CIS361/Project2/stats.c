#include "stats.h"

/*

// I want to be able to write code like this:

StatsCounter total_time_stats;

init_statscounter(&total_time_stats);

// Inside the loop, somewhere.
add_sample_statscounter(&total_time_stats, c->exit_time - c->enter_time);


//At the end:
printf("%f\n", get_avg_statscounter(&total_time_stats));
printf("%f\n", get_max_statscounter(&total_time_stats));
printf("%f\n", get_std_statscounter(&total_time_stats));




// Using StatsCounter for getting total customers:
get_count_statscounter(&total_time_stats);

// Alternatively:
StatsCounter customer_count_stats;

init_statscounter(&customer_count_stats);

// Inside the loop, somewhere.
add_sample_statscounter(&customer_count_stats, 1);

// At the end:
printf("%f\n", get_sum_statscounter(&customer_count_stats));
printf("%f\n", customer_count_stats.sum);

*/


void init_statscounter(StatsCounter* sc){
    sc->count = 0;
    sc->sum = 0;
    sc->min = 0;
    sc->max = 0;
}

void add_sample_statscounter(StatsCounter* sc, int sample) {
    sc->count++;
    sc->sum += sample;
    if (sample < sc->min) {
        sc->min = sample;
    }
    if (sample > sc->max) {
        sc->max = sample;
    }
}

double get_avg_statscounter(const StatsCounter* sc) {
    return sc->sum / sc->count;
}
double get_max_statscounter(const StatsCounter* sc) {
    return sc->max;
}
double get_sum_statscounter(const StatsCounter* sc) {
    return sc->sum;
}
double get_count_statscounter(const StatsCounter* sc) {
    return sc->count;
}
