package com.servebeer.please.solarmax.connector;

class SolarmaxCommand {

    private int number;
    private SolarmaxCommands.SolarmaxCommandKey key;
    private String description;
    private String something;


    /**
     * hide the default constructor
     */
    private SolarmaxCommand() {
    }

    SolarmaxCommand(final int number, final SolarmaxCommands.SolarmaxCommandKey key, final String description, final String something) {
        this.number = number;
        this.key = key;
        this.description = description;
        this.something = something;
    }

    public int getNumber() {
        return number;
    }

    public SolarmaxCommands.SolarmaxCommandKey getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public String getSomething() {
        return something;
    }

}
