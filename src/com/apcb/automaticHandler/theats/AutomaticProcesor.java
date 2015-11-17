/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.apcb.automaticHandler.theats;

import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;


/**
 *
 * @author Demian
 */
public class AutomaticProcesor extends Thread{
     private final static Logger log = Logger.getLogger(AutomaticProcesor.class);
    int timeSleep;
    int repeat;
    boolean finish;
    
    public static void main(String[] args) throws Exception {
        AutomaticProcesor auto;
        
        HashMap<String,String> configArgs = new HashMap<>();
        
        configArgs.put("timeSleep", "1");
        configArgs.put("repeat", "0");
        configArgs.put("name", "AutomaticProcesor");
        
        for (String arg : args) {
            try {
                String[] keyValue = arg.split("=");
                if (configArgs.get(keyValue[0])!=null){
                    configArgs.put(keyValue[0], keyValue[1]); 
                } else {
                    log.debug("argumento no reconocido = "+arg);
                }   
            } catch (Exception e) {
                log.debug("argumento incorrecto = "+arg);
            }    
        }
        
        auto = new AutomaticProcesor(configArgs.get("timeSleep"),configArgs.get("repeat"),configArgs.get("name")); 
        auto.start();
    
    }
    

    public AutomaticProcesor(String timeSleep, String repeat, String name) {
        this.timeSleep = new Integer(timeSleep)*1000;
        this.repeat = new Integer(repeat);
        this.setName(name);
        finish = this.repeat>0;  
    }

    @Override
    public void run(){
        log.debug("Iniciando "+this.getName());
        while (repeat > 0 || !finish){
        //for(int i=0;i<repeat;i++){
            log.debug("<"+new Date()+"> "+this.getName()+" : "+(repeat*-1));
            
            
            try {
                AutomaticProcesor.sleep(timeSleep);
            } catch (InterruptedException ex) {
                //Logger.getLogger(AutomaticProcesor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            repeat--;
            
        }
    }
    
    
}
