import java.util.List;
import java.util.Map.Entry;


public interface DecisionTreeInterface {

    /**
     * select path at a node
     */
	int decide(int decision, int verbose);
    /**
     * select path at a node with input value
     */
	int decide(int decision, String value);

    /**
     * return conclusion for given list of params
     */
	String conclude(List<String> params);
	String conclude_verbose(String[] params);
	String conclude(int decision);

}
