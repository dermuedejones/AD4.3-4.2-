import java.net.URL;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Test;


public class Tree_erstellen_beispiele {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* ben�tigte Listen um manuell einen baum zu erstellen 
		 * */
		List<String> decisions = new LinkedList<String>();
		List<Entry<Integer,Integer>> next_decisions = new LinkedList<Entry<Integer,Integer>>();
		List<String> conclusions = new LinkedList<String>();
		List<String> parameters = new LinkedList<String>();
		
		/*zum parsen der parameter aus der csv*/
		List<List<String>> params;
		
		System.out.println("\n------------------------Von Hand erstellter Decision Tree---------------------------------------\n");
		//reihenfolge decisions wichtig unten f�r die �berg�nge, reihenfolge der parameter muss die hier vorgegebene reihenfolge haben.
		DecisionTree TestTree = new DecisionTree(8, 8);
		TestTree.setParameter("test", 1);
		decisions.add("$1==J");
		TestTree.setParameterDescription(1, "Eine boolsche variable die anzeigt ob das Bild selbst erstellt wurde (J/N)");
		TestTree.setDecisionDescription(1, "Hast du das bild Selbst erstellt?");
		decisions.add("$2==J");
		TestTree.setParameterDescription(2, "Eine boolsche variable die anzeigt ob das Bild unter einer freien Lizenz ver�ffenlicht werdne soll (J/N)");
		TestTree.setDecisionDescription(2, "Willst du es unter einer freien Lizenz ver�ffenlichen?");
		decisions.add("$3>100");
		TestTree.setParameterDescription(3, "Integer: Das Alter des Bildes (J/N)");
		TestTree.setDecisionDescription(3, "Ist das Bild �lter als 100 Jahre?");
		decisions.add("$4==J");
		TestTree.setParameterDescription(4, "Boolean: Urheber bekannt? (J/N)");
		TestTree.setDecisionDescription(4, "Ist der Urheber des Bildes bekannt?");
		decisions.add("$5==J");
		TestTree.setParameterDescription(5, "Boolean: Bildrechte dritter Auszuschlie�en? (J/N)");
		TestTree.setDecisionDescription(5, "Sind Bildrechte dritter Auszuschlie�en?");
		decisions.add("$6>70");
		TestTree.setParameterDescription(6, "Integer: vor wieviel Jahren ist der Urheber verstorben?");
		TestTree.setDecisionDescription(6, "Ist der Urheber vor mehr als 70 Jahren verstorben?");
		decisions.add("$7==J");
		TestTree.setParameterDescription(7, "Boolean: Einverst�ndnis aller betroffenen? (J/N)");
		TestTree.setDecisionDescription(7, "Hast du das Einverst�ndnis aller betroffenen?");
		decisions.add("$8==J");
		TestTree.setParameterDescription(8, "Boolean: Zustimmung des Urhebers? (J/N)");
		TestTree.setDecisionDescription(8, "Hat der Urheber zugestimmt das Bild unter eine freie Lizenz zu stellen?");
		TestTree.setDecisions(decisions);
		conclusions.add("hochladen");
		conclusions.add("nicht hochladen");
		TestTree.setConclusions(conclusions);
		//�berg�nge: indizies der n�chsten decisions bei oben angegebener reihenfolge (start bei 1) 
		//negative zahlen: der betrag ist der index einer conclusion, auch hier reihenfolge beachten 
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(3, 2)); 
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(-2, 5));
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(4, -1));
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(-2, 6));
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(7, -1));
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(8, 5));
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(-2, -1));
		next_decisions.add(new AbstractMap.SimpleEntry<Integer,Integer>(-2, -1));
		TestTree.setNext_decisions(next_decisions);
		//parameter m�ssen in der oben angegeben reihenfolge hinzugef�gt werden.
		parameters.add("N");
		parameters.add("J");
		parameters.add("70");
		parameters.add("J");
		parameters.add("N");
		parameters.add("80");
		parameters.add("N");
		parameters.add("J");
		
		
		System.out.println(TestTree.conclude_verbose(parameters.toArray(new String[parameters.size()])));
		
		//hier das gleiche setup wie oben, blo� die konfigurations f�r den baum aus der csv geparst und statt J/N eben 1/0
		//keine descriptions
		System.out.println("\n---------------------------Aus der csv geparster Decision tree------------------------------------\n");
		TestTree = Parser.parseTreeCsv(".\\bild.csv");
		parameters = new LinkedList<String>();
		parameters.add("0");
		parameters.add("1");
		parameters.add("70");
		parameters.add("1");
		parameters.add("0");
		parameters.add("80");
		parameters.add("0");
		parameters.add("1");
		System.out.println(TestTree.conclude(parameters));
		
		
		//alles aus der csv geparst
		System.out.println("\n-------------------------------Aus der csv geparster Decision tree und geparste parameter------------------------\n");
		params = Parser.parseParameters(".\\bild_cases.csv");
		for(List<String> param : params) {
			System.out.println(TestTree.conclude(param));
		}

	}

}
