public static Set getIdentifiers(String filename) throws Exception{
        String[] keywordsArray = { "IF", "WRITE", "READ", "RETURN", "BEGIN","END", "MAIN", "INT", "REAL" };
        Set keywords = new HashSet();
        Set identifiers = new HashSet();
        for (String s : keywordsArray) {
        keywords.add(s);
        }

        FileReader reader = new FileReader(filename);
        BufferedReader br = new BufferedReader(reader);
        String line;
        identifiers.add("");

        while ((line = br.readLine()) != null) {
        int i=0,n = line.length();
        while(i<line.length()){
        if(line.charAt(i)=='\"'){
        i += 1;
        while(i<n && line.charAt(i)!='\"'){
        i+=1;
        }
        }
        String word = "";
        while(i<n && isLetterOrDigit(line.charAt(i))){
        word+=line.charAt(i);
        i+=1;

        }
        identifiers.add(word);
        i+=1;
        }
        }
        for (String keys :keywordsArray)
        identifiers.remove(keys);
        identifiers.remove("");
        return identifiers;
}