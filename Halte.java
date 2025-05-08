package ov;

public class Halte {
 private String naam;
 private boolean isStartpunt;

 public Halte(String naam, boolean isStartpunt) {
     this.naam = naam;
     this.isStartpunt = isStartpunt;
 }

 public String getNaam() {
     return this.naam;
 }

 public boolean isStartpunt() {
     return this.isStartpunt;
 }
}