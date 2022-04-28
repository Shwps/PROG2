class Person{
    private Persnr pnr;
    private String namn;
    private int vikt;

    public Person(Persnr pnr, String namn, int vikt){
	this.pnr = pnr; 
	this.namn = namn; 
	this.vikt = vikt;
    }

    public Persnr getPnr() { 
	return pnr; 
    }
    public String getNamn() { 
	return namn; 
    }
    public int getVikt() { 
	return vikt; 
    }

    public void äter(int kg){
	vikt += kg;
    }

    public String toString(){
	return pnr + " " + namn + " " + vikt;
    }
}
