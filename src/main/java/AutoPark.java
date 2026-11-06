import java.util.*;

public class AutoPark {
    private String name;
    private List<Item> stock;
    private double revenue;
    private HashMap<Item, Integer> curCart;
    private int numSales;
    private double salesField;
    private List<Item> popularItems;

    public AutoPark(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new ArrayList<Item>();
        curCart = new HashMap<Item, Integer>();
        numSales = 0;
        salesField = 0.0;
        popularItems = new ArrayList<Item>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getStock() {
        return stock;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getNumSales() {
        return numSales;
    }

    public double getSalesField() {
        return salesField;
    }

    public HashMap<Item, Integer> getCurCart() {
        return curCart;
    }

    public List<Item> getPopularItems() {
        return popularItems;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addItem(Item newItem) {
        stock.add(newItem);
        return true;
    }

    public boolean addStock(Item p) {
        for (Item product : stock) {
            if (product.toString().equals(p.toString())) {
                product.setInvQuantity(1);
                return true;
            }
        }
        return false;
    }

    public boolean addToCart(Item p) {
        int previousQuantity = curCart.getOrDefault(p, 0);
        if (p.getInvQuantity() > 1) {
            curCart.put(p, previousQuantity + 1);
            p.setInvQuantity(-1);
            return true;
        } else if (p.getInvQuantity() == 1) {
            curCart.put(p, previousQuantity + 1);
            stock.remove(p);
            return true;
        }
        return false;
    }


    public boolean removeFromCart(String s) {
        Item selectedItem = null;

        for (Item item : curCart.keySet()) {
            String cartEntry = curCart.get(item) + " X " + item;

            if (cartEntry.equals(s)) {
                selectedItem = item;
                break;
            }
        }

        // Return false if the item was not found
        if (selectedItem == null) {
            return false;
        }

        int quantity = curCart.get(selectedItem);

        // Remove one unit from the cart
        if (quantity == 1) {
            curCart.remove(selectedItem);
        } else {
            curCart.put(selectedItem, quantity - 1);
        }

        // Restore one unit to inventory
        if (stock.contains(selectedItem)) {
            addStock(selectedItem);
        } else {
            selectedItem.setInvQuantity(1);
            addItem(selectedItem);
        }

        return true;
    }

    public double cartPrice() {
        double total = 0;
        for (Item o : curCart.keySet()) {
            total += o.getPrice() * curCart.get(o);
        }
        return total;
    }

    public void cashOut() {
        revenue += cartPrice();
        sortCart();
        numSales++;
        salesField = revenue / numSales;
        curCart.clear();
    }

    public void sortCart(){
        for(Item o : curCart.keySet()){
            o.sellUnits(curCart.get(o));
            if(!popularItems.contains(o)){
                popularItems.add(o);
            }
        }
        Collections.sort(popularItems, new Comparator<Item>() {
            public int compare(Item o1, Item o2) {
                return Integer.compare(o2.getSoldQuantity(), o1.getSoldQuantity());
            }
        });
    }
    public static AutoPark createPark(){
        AutoPark park1 = new AutoPark("VroomVille Vehicle Haven");
        Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park1.addItem(s1);
        park1.addItem(s2);
        SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true,32000,10);
        SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false,21000,10);
        park1.addItem(suv1);
        park1.addItem(suv2);
        Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods",true,28000,10);
        Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment",false,30000,10);
        park1.addItem(t1);
        park1.addItem(t2);
        Van v1 = new Van("Ford", "Transit", 2020, "goods",true,22000,10);
        Van v2 = new Van("Ram", "ProMaster", 2019, "equipment",false,19000,10);
        park1.addItem(v1);
        park1.addItem(v2);
        Tire tire1 = new Tire(10,30,true,390, 20);
        Tire tire2 = new Tire(12,35,false,320,20);
        park1.addItem(tire1);
        park1.addItem(tire2);
        return park1;
    }

}



