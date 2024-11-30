package dataLoader;

public class CSVFields {

    private final String label;
    private final String type;
    private String value;
    private final Boolean autoFill;

    public CSVFields(String label, String type, String value, Boolean autoFill) {
        this.label = label;
        this.type = type;
        this.value = value;
        this.autoFill = autoFill;
    }

    public CSVFields(String label, String type, Boolean autoFill) {
        this.label = label;
        this.type = type;
        this.autoFill = autoFill;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Boolean getAutoFill() {
        return autoFill;
    }

    @Override
    public String toString() {
        return "CSVFields [label=" + label + ", type=" + type + ", autoFill=" + autoFill + "]";
    }
}
