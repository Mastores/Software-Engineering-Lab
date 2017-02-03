import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;

import net.miginfocom.swing.MigLayout;


public class ClientsWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txt_firstName;
	private JTextField txt_lastName;
	private JTextField txt_phoneNumber;
	private JTable table_2;
	private JButton btn_delete;
	private JButton btn_update;
	private JLabel lbl_firstName;
	private JLabel lbl_lastName;
	private JLabel lbl_phoneNumber;


	Object[] columns = {"ID", "Όνομα", "Επώνυμο", "Αριθμός τηλεφώνου", "e-mail"};
	DefaultTableModel model = new DefaultTableModel();
	private JButton btn_select;
	private JButton btn_showClients;
	private JButton btn_search;

	int selectedRow = -1;
	private JTextField txt_id;
	private JLabel lbl_id;
	private JTextField txt_email;
	private JLabel lbl_email;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientsWindow frame = new ClientsWindow(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	public ClientsWindow(boolean showSelectButton) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 685, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][][grow][][][][]"));		//columns, rows
		setContentPane(contentPane);		
		
		lbl_id = new JLabel("ID");
		contentPane.add(lbl_id, "cell 0 0");
		
		lbl_firstName = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1");
		contentPane.add(lbl_firstName, "cell 1 0");
		
		lbl_lastName = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");
		contentPane.add(lbl_lastName, "cell 2 0");
		
		lbl_phoneNumber = new JLabel("\u0391\u03C1\u03B9\u03B8\u03BC\u03CC\u03C2 \u03C4\u03B7\u03BB\u03B5\u03C6\u03CE\u03BD\u03BF\u03C5");
		contentPane.add(lbl_phoneNumber, "cell 3 0");
		
		
		
		


		
		
		JButton btn_add = new JButton("Προσθήκη");
		contentPane.add(btn_add, "flowx,cell 0 6,growx");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String firstName = txt_firstName.getText();
					String lastName = txt_lastName.getText();
					String phoneNumber = txt_phoneNumber.getText();
					String email = txt_email.getText();
					
					if (Client.addClient(firstName, lastName, phoneNumber, email)) {				
						showClients(Client.getClients());		//refresh table
					} else {
						MsgBox.infoBox("Δεν έχετε συμπληρώσει όλα τα απαραίτητα πεδία ή υπάρχει ήδη πελάτης με παρόμοια στοιχεία", "Σφάλμα κατά την πρόσθεση πελάτη");
					}
			}
		});
		
	
		

		lbl_email = new JLabel("e-mail");
		contentPane.add(lbl_email, "cell 4 0");
		
		txt_id = new JTextField();
		contentPane.add(txt_id, "cell 0 1,growx");
		txt_id.setColumns(10);
		
		txt_firstName = new JTextField();
		contentPane.add(txt_firstName, "cell 1 1,growx");
		txt_firstName.setColumns(10);
		
		txt_lastName = new JTextField();
		contentPane.add(txt_lastName, "cell 2 1,growx");
		txt_lastName.setColumns(10);
		
		txt_phoneNumber = new JTextField();
		contentPane.add(txt_phoneNumber, "cell 3 1,growx");
		txt_phoneNumber.setColumns(10);
		
		txt_email = new JTextField();
		contentPane.add(txt_email, "cell 4 1,growx");
		txt_email.setColumns(20);
		model.setColumnIdentifiers(columns);
		table_2 = new JTable();	
		contentPane.add(table_2, "cell 0 3 5 1,grow");	
		JTableHeader header = table_2.getTableHeader();
		contentPane.add(header, "cell 0 2,span,growx");		//merge columns with span			
		table_2.setModel(model);
		table_2.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		         selectedRow = table_2.rowAtPoint(evt.getPoint());
		        //int col = table_2.columnAtPoint(evt.getPoint());
		        if (selectedRow >= 0) {
		           txt_id.setText(model.getValueAt(selectedRow,0).toString());
		           txt_firstName.setText(model.getValueAt(selectedRow,1).toString());
		           txt_lastName.setText(model.getValueAt(selectedRow,2).toString());
		           txt_phoneNumber.setText(model.getValueAt(selectedRow,3).toString());
		           txt_email.setText(model.getValueAt(selectedRow,4).toString());
		        }
		    }
		});
		
		
		
		btn_search = new JButton("Αναζήτηση");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String strId = txt_id.getText();
				String firstName = txt_firstName.getText();
				String lastName = txt_lastName.getText();
				String phoneNumber = txt_phoneNumber.getText();
				String email = txt_email.getText();
				
				int id = AboutApp.stringToInteger(txt_id.getText());
				
				ArrayList<Client> clientResults = Client.searchClient(id, firstName, lastName, phoneNumber, email);
				showClients(clientResults);
			}
		});
		
		btn_delete = new JButton("Διαγραφή");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRow >= 0) {	
					int id = AboutApp.stringToInteger(model.getValueAt(selectedRow,0).toString());
					if (Client.deleteClient(id)) {
						showClients(Client.getClients());
					} else {
						MsgBox.infoBox("Δεν ήταν δυνατή η διαγραφή του πελάτη", "Αδυναμία διαγραφής πελάτη");
					}
				}
			}
		});
		contentPane.add(btn_delete, "cell 1 6,growx");
		contentPane.add(btn_search, "cell 2 6,growx");
		
		btn_update = new JButton("Ανανέωση εγγραφής");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = AboutApp.stringToInteger(model.getValueAt(selectedRow,0).toString());
				String firstName = txt_firstName.getText();
				String lastName = txt_lastName.getText();
				String phoneNumber = txt_phoneNumber.getText();
				String email = txt_email.getText();
				
				if (Client.updateClient(id, firstName, lastName, phoneNumber, email)) {		//ArrayList with the updated customer, for visula comfirmation of the change
				    showClients(Client.getClients());		
				} else {
					MsgBox.infoBox("Δεν ήταν δυνατή η ανανέωση του πελάτη", "Αδυναμία ανανέωσης πελάτη");
				}
			}
		});
		contentPane.add(btn_update, "cell 3 6,growx");
		
		btn_showClients = new JButton("Ανανέωση φόρμας");
		btn_showClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showClients(Client.getClients());	
			}
		});
		contentPane.add(btn_showClients, "cell 4 6,growx");
		
		if (showSelectButton) {
			btn_select = new JButton("Επιλογή");
			contentPane.add(btn_select, "cell 2 7,growx");
		}
		

	}
	
	
	private void showClients(ArrayList<Client> clientsList) {	
		model.setRowCount(0);			//clear JTable
			
		if (clientsList != null) {
			for (int i = 0; i < clientsList.size(); i++){
				   int id = clientsList.get(i).getID();
				   String firstName = clientsList.get(i).getFirstName();
				   String lastName = clientsList.get(i).getLastName();
				   String phoneNumber = clientsList.get(i).getPhoneNumber();
				   String email = clientsList.get(i).getEmail();
					   
				   Object[] data = {id, firstName, lastName, phoneNumber, email};
		
				   model.addRow(data);
			}
		}
	}
	
	
	

}
