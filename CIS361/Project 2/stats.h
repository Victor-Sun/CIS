typedef struct StatsCounter StatsCounter;


void init_statscounter(StatsCounter* sc);
void add_sample_statscounter(StatsCounter* sc, int sample);
double get_avg_statscounter(StatsCounter* sc);
double get_max_statscounter(StatsCounter* sc);
double get_sum_statscounter(StatsCounter* sc);