package information;

public class NutritionInfo {
    private String name;
    private double calories;
    private double fiber;
    private double protein;

    public NutritionInfo(){}

    public NutritionInfo(String string){
        String[] strings = string.split("`");

        name = strings[0];
        calories = Double.parseDouble(strings[1]);
        fiber = Double.parseDouble(strings[2]);
        protein = Double.parseDouble(strings[3]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public String toString(){
        return name + "`" + calories + "`" + fiber + "`" + protein;
    }
}
