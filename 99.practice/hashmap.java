import java.util.HashMap;
import java.util.ArrayList;

public class hashmap {

    public static void set1(){
    HashMap<String,Integer> map = new HashMap<>();
        map.put("IND",10000);
        map.put("USA", 1000);
        map.put("NEP", 100);
        map.put("UK", 990);
        map.put("IND", 1000000);
        
        // System.out.println(map.get("IND"));
        // System.out.println(map.getOrDefault("iND",0));
        // System.out.println(map.keySet()); //O(n)
       
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        for(String s:keys)
        {
            // if(s == "IND")
            // {
            //     map.put(s, 000);
            // }
            System.out.println(s + ": " + map.get(s));
        }

        System.out.println( map.containsKey("IND"));
        // System.out.println(map);
    }


    public static void freqMap(String str)
    { 
    HashMap<Character,Integer> map = new HashMap<>();
    for(int i=0;i<str.length();i++)
    {
        char ch = str.charAt(i);
        map.put(ch, map.getOrDefault(ch, 0)+1);

        // if(map.containsKey(ch))
        // {
        //     map.put(ch, map.get(ch)+1);
        // }else{
        //     map.put(ch, 1);
        // }

        // dont use nullptr exception
        // map.put(ch, map.get(ch)+1);
    }
    System.out.println(map);
    }

    public static void freqMap2(String str){
        HashMap<Character,ArrayList<Integer>> map = new HashMap<>();
        for(int i=0;i<str.length();i++)
        {
            char ch = str.charAt(i);
            if(map.containsKey(ch))
            {
                map.get(ch).add(i);
            }else{
                map.put(ch, new ArrayList<>());
            }

        }
        // System.out.println(map);
        ArrayList<Character> keys = new ArrayList<>(map.keySet());
        for(Character s:keys)
        {
            System.out.println(s + ":" + map.get(s));
            System.out.println();
        }
    }

    public static void main(String args[]) {
        // set1();
        // freqMap("SAHITHHHSSSIIAARRTTTTTTAAA");
        freqMap2("SAHITHHHSSSIIAARRTTTTTTAAA");
    }

}
