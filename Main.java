class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        System.out.println("Value for key 'apple': " + map.get("apple"));
        System.out.println("Value for key 'banana': " + map.get("banana"));
        System.out.println("Value for key 'cherry': " + map.get("cherry"));

        map.remove("banana");
        System.out.println("Value for key 'banana' after removal: " + map.get("banana"));
    }
}