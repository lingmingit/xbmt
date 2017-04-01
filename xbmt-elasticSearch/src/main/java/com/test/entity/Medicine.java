package com.test.entity;



/***
 * 
 * @author LingMin 
 * @date 2016-10-26<br>
 * @version 1.0<br>
 */
public class Medicine {

	
	private Integer id;
    private String name;
    private String function;
    
    public Medicine() {
        super();
    }

    /***
     * 
     * @param id
     * @param name
     * @param function
     */
    public Medicine(Integer id, String name, String function) {
        super();
        this.id = id;
        this.name = name;
        this.function = function;
    }

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}
    
    
    
}
