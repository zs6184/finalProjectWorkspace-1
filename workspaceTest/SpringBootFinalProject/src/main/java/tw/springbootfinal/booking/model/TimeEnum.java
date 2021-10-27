package tw.springbootfinal.booking.model;

public enum TimeEnum {
	 T1("1", "11:00"),
	    T2("2", "12:00"),
	    T3("3", "13:00"),
	    T4("4", "14:00"),
	    T5("5", "15:00"),
	    T6("6", "16:00"),
	    T7("7", "17:00"),
	    ERR("99", ""),

	    ;
	    private final String code;
	    private final String value;

	    TimeEnum(String code, String value) {
	        this.code = code;
	        this.value = value;
	    }

	    public String getCode() {
	        return code;
	    }

	    public String getValue() {
	        return value;
	    }

	    public static String getValueByCode(String code){
	        for (TimeEnum timeEnum : TimeEnum.values()){
	            if (timeEnum.getCode().equals(code)) return timeEnum.getValue();
	        }
	        return ERR.getValue();
	    }
	}


