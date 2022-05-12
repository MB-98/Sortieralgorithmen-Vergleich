import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class SortGUI extends Sortieralgorithmen {

	String startArraySt = "";
	String bubbleArraySt = "";
	String quickArraySt = "";
	String insertArraySt = "";
	String selectArraySt = "";
	JTextField startArrayField;
	JTextField bubbleArrayField;
	JTextField quickArrayField;
	JTextField insertArrayField;
	JTextField selectArrayField;
	JLabel bubbleLabel;
	JLabel quickLabel;
	JLabel insertLabel;
	JLabel selectLabel;
	JTextField bubbleWert;
	JTextField quickWert;
	JTextField insertWert;
	JTextField selectWert;
	JPanel graphPanel;
	CartesianPanel panel;
	JRadioButton radioAuf;
	JRadioButton radioAb;

	int[] startArray;
	int[] bubbleArray;
	int[] quickArray;
	int[] insertArray;
	int[] selectArray;

	String sort = "aufsteigend";

	int arraySize;
	int repeatNum;
	long bubbleTime;
	long quickTime;
	long insertTime;
	long selectTime;

	long bubbleTimeGes = 0;
	long quickTimeGes = 0;
	long insertTimeGes = 0;
	long selectTimeGes = 0;

	public DataObject do_;

	public ArrayList<Integer> arrayList;

	public static void main(String[] args) {
		new SortGUI();

	}

	public SortGUI() {
		JFrame frame = new JFrame("Sortierer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setSize(1100, 700);

		this.do_ = new DataObject();
		arrayList = new ArrayList<Integer>();

		JPanel leftPanel = createMainPanel();
		JPanel rightPanel = createMainPanel();
		createSplitPane(frame, leftPanel, rightPanel);

		JPanel arrayPanel = new JPanel();
		arrayPanel.setLayout(new BorderLayout());
		arrayPanel.setBorder(BorderFactory.createTitledBorder("StartArray"));
		leftPanel.add(arrayPanel, BorderLayout.CENTER);
		addLabelToPane(arrayPanel, "Anzahl der Array Werte:");

		JTextField anzahlWerte = addTextFieldToPane(arrayPanel);
		anzahlWerte.setHorizontalAlignment(JTextField.CENTER);
		anzahlWerte.setText("10");
		arrayPanel.add(anzahlWerte);

		JButton arraybtn = addButtonToPane(arrayPanel, " Array erstellen", frame);
		arraybtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				arraySize = getArraySize(anzahlWerte);
				startArray = randomNum(arraySize);
				displayArray(startArray, startArraySt, startArrayField);

				System.out.println(" ");

				// Arrays für die verschiedenen Sortieralgorithmen

				bubbleArray = copyArray(startArray);
				quickArray = copyArray(startArray);
				insertArray = copyArray(startArray);
				selectArray = copyArray(startArray);

			}
		});

		arrayPanel.add(arraybtn);

		space(leftPanel);

		JPanel sortPanel = new JPanel();
		sortPanel.setLayout(new BorderLayout());
		sortPanel.setBorder(BorderFactory.createTitledBorder("Sortieralgorithmen"));
		leftPanel.add(sortPanel, BorderLayout.CENTER);
		radioAuf = new JRadioButton("aufsteigend", true);
		radioAuf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sort = "aufsteigend";
				radioAb.setSelected(false);
				radioAuf.setSelected(true);
			}
		});
		radioAb = new JRadioButton("absteigend", false);
		radioAb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sort = "absteigend";
				radioAuf.setSelected(false);
				radioAb.setSelected(true);
			}
		});
		sortPanel.add(radioAuf);
		sortPanel.add(radioAb);
		JButton allbtn = addButtonToPane(sortPanel, "Alle durchführen", frame);
		allbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bubbleTime = bubbleSort(bubbleArray, bubbleTime, sort);
				displayArray(bubbleArray, bubbleArraySt, bubbleArrayField);
				String bubbleTimeSt = String.valueOf(bubbleTime);
				bubbleLabel.setText("Verlaufszeit der Bubble-Sort Schleife: " + bubbleTimeSt + " Nanosek.");
				quickTime = quickSort(quickArray, quickTime, sort);
				displayArray(quickArray, quickArraySt, quickArrayField);
				String quickTimeSt = String.valueOf(quickTime);
				quickLabel.setText("Verlaufszeit der Quick-Sort Schleife: " + quickTimeSt + " Nanosek.");
				insertTime = insertSort(insertArray, insertTime, sort);
				displayArray(insertArray, insertArraySt, insertArrayField);
				String insertTimeSt = String.valueOf(insertTime);
				insertLabel.setText("Verlaufszeit der Insertion-Sort Schleife: " + insertTimeSt + " Nanosek.");
				selectTime = selectionsort(selectArray, selectTime, sort);
				displayArray(selectArray, selectArraySt, selectArrayField);
				String selectTimeSt = String.valueOf(selectTime);
				selectLabel.setText("Verlaufszeit der Selection-Sort Schleife: " + selectTimeSt + " Nanosek.");
			}
		});

		JButton bubblebtn = addButtonToPane(sortPanel, " Bubble-Sort", frame);
		bubblebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bubbleTime = bubbleSort(bubbleArray, bubbleTime, sort);
				displayArray(bubbleArray, bubbleArraySt, bubbleArrayField);
				String bubbleTimeSt = String.valueOf(bubbleTime);
				bubbleLabel.setText("Verlaufszeit der Insertion-Sort Schleife: " + bubbleTimeSt + " Nanosek.");
			}
		});

		JButton quickbtn = addButtonToPane(sortPanel, " Quick-Sort", frame);
		quickbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quickTime = quickSort(quickArray, quickTime, sort);
				displayArray(quickArray, quickArraySt, quickArrayField);
				String quickTimeSt = String.valueOf(quickTime);
				quickLabel.setText("Verlaufszeit der Quick-Sort Schleife: " + quickTimeSt + " Nanosek.");
			}
		});

		JButton insertbtn = addButtonToPane(sortPanel, " Insertion-Sort", frame);
		insertbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertTime = insertSort(insertArray, selectTime, sort);
				displayArray(insertArray, insertArraySt, insertArrayField);
				String insertTimeSt = String.valueOf(insertTime);
				insertLabel.setText("Verlaufszeit der Insertion-Sort Schleife: " + insertTimeSt + " Nanosek.");
			}
		});

		JButton selectbtn = addButtonToPane(sortPanel, " Selection-Sort", frame);
		selectbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectTime = selectionsort(selectArray, selectTime, sort);
				displayArray(selectArray, selectArraySt, selectArrayField);
				String selectTimeSt = String.valueOf(selectTime);
				selectLabel.setText("Verlaufszeit der Selection-Sort Schleife: " + selectTimeSt + " Nanosek.");
			}
		});
		JButton clearbtn1 = addButtonToPane(sortPanel, "Clear", frame);
		clearbtn1.setForeground(Color.black);
		clearbtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startArrayField.setText("");
				bubbleArrayField.setText("");
				quickArrayField.setText("");
				insertArrayField.setText("");
				selectArrayField.setText("");
			}
		});
		sortPanel.add(allbtn);
		sortPanel.add(bubblebtn);
		sortPanel.add(quickbtn);
		sortPanel.add(insertbtn);
		sortPanel.add(selectbtn);
		sortPanel.add(clearbtn1);

		space(leftPanel);

		JPanel statsPanel = new JPanel();
		statsPanel.setLayout(new BorderLayout());
		statsPanel.setBorder(BorderFactory.createTitledBorder("Durchschnitslaufzeiten"));
		leftPanel.add(statsPanel, BorderLayout.CENTER);
		addLabelToPane(statsPanel, "Anzahl der Durchführungen:");
		JTextField anzahlDurchführung = addTextFieldToPane(statsPanel);
		anzahlDurchführung.setHorizontalAlignment(JTextField.CENTER);
		anzahlDurchführung.setText("10");
		statsPanel.add(anzahlDurchführung);
		JButton allstatsbtn = addButtonToPane(statsPanel, "Alle durchführen", frame);
		allstatsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repeatNum = Integer.parseInt(anzahlDurchführung.getText());
				for (int i = 0; i < repeatNum; i++) {
					arraySize = getArraySize(anzahlWerte);
					startArray = randomNum(arraySize);
					bubbleArray = copyArray(startArray);
					quickArray = copyArray(startArray);
					insertArray = copyArray(startArray);
					selectArray = copyArray(startArray);
					bubbleTime = bubbleSort(bubbleArray, bubbleTime, sort);
					bubbleTimeGes = bubbleTimeGes + bubbleTime;
					quickTime = quickSort(quickArray, quickTime, sort);
					quickTimeGes = quickTimeGes + quickTime;
					insertTime = insertSort(insertArray, insertTime, sort);
					insertTimeGes = insertTimeGes + insertTime;
					selectTime = selectionsort(selectArray, selectTime, sort);
					selectTimeGes = selectTimeGes + selectTime;
				}

				calculateAverageValue(bubbleTimeGes, repeatNum, bubbleWert);
				calculateAverageValue(quickTimeGes, repeatNum, quickWert);
				calculateAverageValue(insertTimeGes, repeatNum, insertWert);
				calculateAverageValue(selectTimeGes, repeatNum, selectWert);

				bubbleTimeGes = 0;
				quickTimeGes = 0;
				insertTimeGes = 0;
				selectTimeGes = 0;
			}

		});
		JButton bubblestatsbtn = addButtonToPane(statsPanel, " Bubble-Sort", frame);
		bubblestatsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repeatNum = Integer.parseInt(anzahlDurchführung.getText());
				for (int i = 0; i < repeatNum; i++) {
					arraySize = getArraySize(anzahlWerte);
					startArray = randomNum(arraySize);
					bubbleArray = copyArray(startArray);
					bubbleTime = bubbleSort(bubbleArray, bubbleTime, sort);
					bubbleTimeGes = bubbleTimeGes + bubbleTime;
				}
				calculateAverageValue(bubbleTimeGes, repeatNum, bubbleWert);
				bubbleTimeGes = 0;
			}
		});

		JButton quickstatsbtn = addButtonToPane(statsPanel, " Quick-Sort", frame);
		quickstatsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repeatNum = Integer.parseInt(anzahlDurchführung.getText());
				for (int i = 0; i < repeatNum; i++) {
					arraySize = getArraySize(anzahlWerte);
					startArray = randomNum(arraySize);
					quickArray = copyArray(startArray);
					quickTime = quickSort(quickArray, quickTime, sort);
					quickTimeGes = quickTimeGes + quickTime;
				}
				calculateAverageValue(quickTimeGes, repeatNum, quickWert);
				quickTimeGes = 0;
			}
		});
		JButton insertstatsbtn = addButtonToPane(statsPanel, " Insertion-Sort", frame);
		insertstatsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repeatNum = Integer.parseInt(anzahlDurchführung.getText());
				for (int i = 0; i < repeatNum; i++) {
					arraySize = getArraySize(anzahlWerte);
					startArray = randomNum(arraySize);
					insertArray = copyArray(startArray);
					insertTime = insertSort(insertArray, insertTime, sort);
					insertTimeGes = insertTimeGes + insertTime;
				}
				calculateAverageValue(insertTimeGes, repeatNum, insertWert);
				insertTimeGes = 0;
			}
		});
		JButton selectstatsbtn = addButtonToPane(statsPanel, " Selection-Sort", frame);
		selectstatsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repeatNum = Integer.parseInt(anzahlDurchführung.getText());
				for (int i = 0; i < repeatNum; i++) {
					arraySize = getArraySize(anzahlWerte);
					startArray = randomNum(arraySize);
					selectArray = copyArray(startArray);
					selectTime = selectionsort(selectArray, selectTime, sort);
					selectTimeGes = selectTimeGes + selectTime;
				}
				calculateAverageValue(selectTimeGes, repeatNum, selectWert);
				selectTimeGes = 0;
			}
		});
		JButton clearbtn = addButtonToPane(statsPanel, "Clear", frame);
		clearbtn.setForeground(Color.black);
		clearbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bubbleWert.setText(" ");
				quickWert.setText(" ");
				insertWert.setText(" ");
				selectWert.setText(" ");
			}
		});

		statsPanel.add(allstatsbtn);
		statsPanel.add(bubblestatsbtn);
		statsPanel.add(quickstatsbtn);
		statsPanel.add(insertstatsbtn);
		statsPanel.add(selectstatsbtn);
		statsPanel.add(clearbtn);

		bubbleWert = addTextFieldToPane(statsPanel);
		quickWert = addTextFieldToPane(statsPanel);
		insertWert = addTextFieldToPane(statsPanel);
		selectWert = addTextFieldToPane(statsPanel);
		bubbleWert.setEnabled(false);
		quickWert.setEnabled(false);
		insertWert.setEnabled(false);
		selectWert.setEnabled(false);
		addLabelToPane(statsPanel, " ");
		addLabelToPane(statsPanel, "Durchschnittsdauer Bubble-Sort:");
		statsPanel.add(bubbleWert);
		addLabelToPane(statsPanel, "Durchschnittsdauer Quick-Sort:");
		statsPanel.add(quickWert);
		addLabelToPane(statsPanel, "Durchschnittsdauer Insertion-Sort:");
		statsPanel.add(insertWert);
		addLabelToPane(statsPanel, "Durchschnittsdauer Selection-Sort:");
		statsPanel.add(selectWert);

		JButton graphbtn = addButtonToPane(statsPanel, "Graph erstellen", frame);
		graphbtn.setForeground(Color.BLACK);
		graphbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				do_.clear();

				for (int j = 1; j <= 600; j++) {

					arraySize = j;

					repeatNum = Integer.parseInt(anzahlDurchführung.getText());
					for (int i = 0; i < repeatNum; i++) {

						startArray = randomNum(arraySize);
						bubbleArray = copyArray(startArray);
						quickArray = copyArray(startArray);
						insertArray = copyArray(startArray);
						selectArray = copyArray(startArray);
						bubbleTime = bubbleSort(bubbleArray, bubbleTime, sort);
						bubbleTimeGes = bubbleTimeGes + bubbleTime;
						quickTime = quickSort(quickArray, quickTime, sort);
						quickTimeGes = quickTimeGes + quickTime;
						insertTime = insertSort(insertArray, insertTime, sort);
						insertTimeGes = insertTimeGes + insertTime;
						selectTime = selectionsort(selectArray, selectTime, sort);
						selectTimeGes = selectTimeGes + selectTime;
					}

					int bs = calculateAverageValue(bubbleTimeGes, repeatNum, bubbleWert);
					int qs = calculateAverageValue(quickTimeGes, repeatNum, quickWert);
					int is = calculateAverageValue(insertTimeGes, repeatNum, insertWert);
					int ss = calculateAverageValue(selectTimeGes, repeatNum, selectWert);

					panel.repaint();

					do_.getBsList().add(bs);
					do_.getQsList().add(qs);
					do_.getIsList().add(is);
					do_.getSsList().add(ss);
					do_.getAnzahlDurchlaeufe().add(arraySize);

					bubbleTimeGes = 0;
					quickTimeGes = 0;
					insertTimeGes = 0;
					selectTimeGes = 0;
				}
			}

		});
		addLabelToPane(statsPanel, " ");
		statsPanel.add(graphbtn);

		space(leftPanel);
		space(leftPanel);

		JPanel startArrayPanel = new JPanel();
		startArrayPanel.setLayout(new BorderLayout());
		startArrayPanel.setBorder(BorderFactory.createTitledBorder("StartArray"));
		rightPanel.add(startArrayPanel, BorderLayout.CENTER);
		startArrayField = addTextFieldToPane(startArrayPanel);
		JScrollPane scrollArray = new JScrollPane(startArrayField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollArray.setMaximumSize(new Dimension(Integer.MAX_VALUE, scrollArray.getPreferredSize().height));
		startArrayField.setEnabled(false);
		startArrayField.setText(startArraySt);
		startArrayPanel.add(scrollArray);

		JPanel bubblePanel = new JPanel();
		bubblePanel.setLayout(new BorderLayout());
		bubblePanel.setBorder(BorderFactory.createTitledBorder("BubbleArray"));
		rightPanel.add(bubblePanel, BorderLayout.CENTER);
		bubbleArrayField = addTextFieldToPane(bubblePanel);
		JScrollPane scrollbubbleArray = new JScrollPane(bubbleArrayField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollbubbleArray.setMaximumSize(new Dimension(Integer.MAX_VALUE, scrollbubbleArray.getPreferredSize().height));
		bubbleArrayField.setEnabled(false);
		bubblePanel.add(scrollbubbleArray);
		bubbleLabel = addLabelToPane(bubblePanel, "Verlaufszeit der Bubble-Sort Schleife: " + bubbleTime + " Nanosek.");
		bubblePanel.add(bubbleLabel);

		JPanel quickPanel = new JPanel();
		quickPanel.setLayout(new BorderLayout());
		quickPanel.setBorder(BorderFactory.createTitledBorder("QuickArray"));
		rightPanel.add(quickPanel, BorderLayout.CENTER);
		quickArrayField = addTextFieldToPane(quickPanel);
		JScrollPane scrollquickArray = new JScrollPane(quickArrayField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollquickArray.setMaximumSize(new Dimension(Integer.MAX_VALUE, scrollquickArray.getPreferredSize().height));
		quickArrayField.setEnabled(false);
		quickPanel.add(scrollquickArray);
		quickLabel = addLabelToPane(quickPanel, "Verlaufszeit der Quick-Sort Schleife: " + quickTime + " Nanosek.");
		quickPanel.add(quickLabel);

		JPanel insertPanel = new JPanel();
		insertPanel.setLayout(new BorderLayout());
		insertPanel.setBorder(BorderFactory.createTitledBorder("InsertionArray"));
		rightPanel.add(insertPanel, BorderLayout.CENTER);
		insertArrayField = addTextFieldToPane(insertPanel);
		JScrollPane scrollinsertArray = new JScrollPane(insertArrayField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollinsertArray.setMaximumSize(new Dimension(Integer.MAX_VALUE, scrollinsertArray.getPreferredSize().height));
		insertArrayField.setEnabled(false);
		insertPanel.add(scrollinsertArray);
		insertLabel = addLabelToPane(insertPanel,
				"Verlaufszeit der Insertion-Sort Schleife: " + insertTime + " Nanosek.");
		insertPanel.add(insertLabel);

		JPanel selectPanel = new JPanel();
		selectPanel.setLayout(new BorderLayout());
		selectPanel.setBorder(BorderFactory.createTitledBorder("SelectionArray"));
		rightPanel.add(selectPanel, BorderLayout.CENTER);
		selectArrayField = addTextFieldToPane(selectPanel);
		JScrollPane scrollselectArray = new JScrollPane(selectArrayField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollselectArray.setMaximumSize(new Dimension(Integer.MAX_VALUE, scrollselectArray.getPreferredSize().height));
		selectArrayField.setEnabled(false);
		selectPanel.add(scrollselectArray);
		selectLabel = addLabelToPane(selectPanel,
				"Verlaufszeit der Selection-Sort Schleife: " + selectTime + " Nanosek.");
		selectPanel.add(selectLabel);

		graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
		graphPanel.setBorder(BorderFactory.createTitledBorder("Graph"));
		panel = new CartesianPanel(this.do_);
		graphPanel.add(panel);
		rightPanel.add(graphPanel, BorderLayout.CENTER);
		selectPanel.add(selectLabel);

		frame.setVisible(true);
	}

	public int calculateAverageValue(long timeGes, int repeatNum, JTextField textfield) {
		long timeErg = timeGes / repeatNum;
		String timeSt = String.valueOf(timeErg);
		arrayList.clear();
		arrayList.add(new Integer(Integer.valueOf(timeSt)));
		System.out.println(arrayList.get(0));
		textfield.setText(timeSt);
		return new Integer(Integer.valueOf(timeSt));
	}

	protected int getArraySize(JTextField anzahlWerte) {
		String temp = anzahlWerte.getText();
		int zahl = Integer.parseInt(temp);
		return zahl;
	}

	void displayArray(int[] array, String arraySt, JTextField arrayField) {
		for (int i = 0; i < array.length; i++) {
			arraySt = arraySt + " / " + String.valueOf(array[i]);
		}
		arrayAnzeigen(arraySt, arrayField);
	}

	void arrayAnzeigen(String arraySt, JTextField arrayField) {
		arrayField.setText(arraySt);
	}

	public JPanel createMainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return p;
	}

	public JSplitPane createSplitPane(JFrame frame, JPanel left, JPanel right) {
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		sp.setOneTouchExpandable(true);
		frame.add(sp, BorderLayout.CENTER);
		return sp;
	}

	public static JLabel addLabelToPane(Container pane, String st) {
		JLabel label = new JLabel(st);
		pane.add(label);
		return label;
	}

	public static JTextField addTextFieldToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		JTextField textfield = addATextField(pane);
		return textfield;
	}

	private static JTextField addATextField(Container pane) {
		JTextField textField = new JTextField();
		textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, textField.getPreferredSize().height));
		textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		return textField;

	}

	public static JButton addButtonToPane(Container pane, String st, Component comp) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		JButton btn = addButton(pane, st, comp);
		return btn;
	}

	private static JButton addButton(Container pane, String st, Component comp) {
		JButton btn = new JButton(st);
		btn.setBackground(Color.WHITE);
		btn.setForeground(Color.BLACK);
		btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btn.getPreferredSize().height));
		btn.setAlignmentX(Component.CENTER_ALIGNMENT);
		return btn;

	}

	private static void space(Container pane) {
		JPanel panel = new JPanel();
		pane.add(panel);
	}

	public DataObject getDataObject() {
		return this.do_;
	}
}