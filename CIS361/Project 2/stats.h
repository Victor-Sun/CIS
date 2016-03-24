typedef struct StatsCounter {
    int count;
    double sum;
    double max;
    double min;
} StatsCounter;


void init_statscounter(StatsCounter* sc);
void add_sample_statscounter(StatsCounter* sc, int sample);
double get_avg_statscounter(const StatsCounter* sc);
double get_max_statscounter(const StatsCounter* sc);
double get_sum_statscounter(const StatsCounter* sc);
double get_count_statscounter(const StatsCounter* sc);