class HelloWithFunctions {
    public static void helloFromFunction(String name){
        String text = "Say Hello to ";
        text += name;
        text += " from Function";
        
        System.out.println(text);
    }
    public static void main(String[] args) {
        helloFromFunction("Marcelo Emanoel");
    }
}