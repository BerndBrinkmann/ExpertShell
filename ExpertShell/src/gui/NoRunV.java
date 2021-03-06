package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class NoRunV extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NoRunV(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setImage(SWTResourceManager.getImage(NoRun.class, "/resources/exclamation_sign.png"));
		shell.setSize(365, 150);
		shell.setText("Error");
		
		Label lblNoKnowledgeBase = new Label(shell, SWT.WRAP);
		lblNoKnowledgeBase.setLocation(10, 10);
		lblNoKnowledgeBase.setSize(341, 105);
		lblNoKnowledgeBase.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblNoKnowledgeBase.setText("Unable to Run. You have chosen Backward Chaining as the run method. Either:\r\n\r\nSelect a Target Variable from the drop down list; or\r\n\r\nSelect Default or Forward Chaining  at the top left of the screen.\r\n\r\n");

	}
}

