import java.util.ArrayList;

public class DataObject {

	private ArrayList<Integer> bsList;
	private ArrayList<Integer> qsList;
	private ArrayList<Integer> isList;
	private ArrayList<Integer> ssList;
	private ArrayList<Integer> anzahlDurchlaeufe;

	public DataObject() {
		super();
		bsList = new ArrayList<>();
		qsList = new ArrayList<>();
		isList = new ArrayList<>();
		ssList = new ArrayList<>();
		anzahlDurchlaeufe = new ArrayList<>();
	}

	public DataObject(ArrayList<Integer> bsList, ArrayList<Integer> qsList, ArrayList<Integer> isList,
			ArrayList<Integer> ssList, ArrayList<Integer> anzahlDurchlaeufe) {
		super();
		this.bsList = bsList;
		this.qsList = qsList;
		this.isList = isList;
		this.ssList = ssList;
		this.anzahlDurchlaeufe = anzahlDurchlaeufe;
	}

	public void clear() {
		this.bsList.clear();
		this.qsList.clear();
		this.isList.clear();
		this.ssList.clear();
		this.anzahlDurchlaeufe.clear();

	}

	public ArrayList<Integer> getBsList() {
		return bsList;
	}

	public void setBsList(ArrayList<Integer> bsList) {
		this.bsList = bsList;
	}

	public ArrayList<Integer> getQsList() {
		return qsList;
	}

	public void setQsList(ArrayList<Integer> qsList) {
		this.qsList = qsList;
	}

	public ArrayList<Integer> getIsList() {
		return isList;
	}

	public void setIsList(ArrayList<Integer> isList) {
		this.isList = isList;
	}

	public ArrayList<Integer> getSsList() {
		return ssList;
	}

	public void setSsList(ArrayList<Integer> ssList) {
		this.ssList = ssList;
	}

	public ArrayList<Integer> getAnzahlDurchlaeufe() {
		return anzahlDurchlaeufe;
	}

	public void setAnzahlDurchlaeufe(ArrayList<Integer> anzahlDurchlaeufe) {
		this.anzahlDurchlaeufe = anzahlDurchlaeufe;
	}

}
