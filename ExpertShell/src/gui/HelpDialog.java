package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class HelpDialog extends Dialog {

	protected Object result;
	protected Shell shlAbout;
	private Text txtThisExpertSystem;
	private Text txtArieWestlandBernd;
	private Text txtForTheRequirements;
	private Text txtAtTheUniversity;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public HelpDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlAbout.open();
		shlAbout.layout();
		Display display = getParent().getDisplay();
		while (!shlAbout.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlAbout = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlAbout.setSize(379, 180);
		shlAbout.setText("About");
		
		txtThisExpertSystem = new Text(shlAbout, SWT.WRAP);
		txtThisExpertSystem.setText("This Expert System Shell was designed by: \r\n\r\nArie Westland, Bernd Brinkmann, Jessica Taylor, Mandy Bester, and Nathan Cortes.\r\n\r\nFor the partial requirements of KNE441: Computational Intelligence, at the University of Tasmania, 2014.");
		txtThisExpertSystem.setBounds(10, 10, 353, 132);
		
	/**	Button btnClose = new Button(shlAbout, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				e.getSource();
				shlAbout.close();
				
				
			}
		});
		
		btnClose.setImage(SWTResourceManager.getImage(HelpDialog.class, "/resources/exit_new.jpg"));
		btnClose.setBounds(298, 127, 75, 25);
		btnClose.setText("Close");*/
		


	}

}
