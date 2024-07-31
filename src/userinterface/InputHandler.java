package userinterface;


public class InputHandler {

    private final String[] fields;
    private boolean error = false;

//----------------------------------------------------------------------------------------------------------------------

    public InputHandler(String line) {
        this.fields = line.split(";");
    }

//----------------------------------------------------------------------------------------------------------------------

    public String get(int n){
        return fields[n];
    }

//----------------------------------------------------------------------------------------------------------------------

    public int countFields(){
        int c = 0;
        for(int i = 1; i < this.fields.length;i++){
            if(this.get(i) != null){
                 c++;
            }
        }
        return c;
    }

//----------------------------------------------------------------------------------------------------------------------

    public int fieldToInteger(int n){
        int result = 0;
        try{
            result = Integer.parseInt(this.get(n));
        }catch (Exception e){
            this.error = true;
        }
        return result;
    }

//----------------------------------------------------------------------------------------------------------------------

    public double fieldToDouble(int n){
        double result = 0;
        try {
            result = Double.parseDouble(this.get(n));
        }catch (Exception e){
            this.error = true;
        }

        return result;
    }

//----------------------------------------------------------------------------------------------------------------------

    public boolean getError(){
        return !this.error;
    }

//----------------------------------------------------------------------------------------------------------------------

}
