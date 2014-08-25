
/**
 * GA represents the process of evolving population algorithms
 */

public class GA {

    private int pop_size, generation, mut_type, cross_type, sel_type;
    private double mut_rate, cross_rate;

    public GA(int pop_size, int generations, double mut_rate,
              int mut_type, double cross_rate, int cross_type, int sel_type) {

        this.pop_size = pop_size;
        this.generation =generations;
        this.mut_rate = mut_rate;
        this.mut_type = mut_type;
        this.cross_rate = cross_rate;
        this.cross_type = cross_type;
        this.sel_type = sel_type;
    }

    public void runGA() {

    }


}
