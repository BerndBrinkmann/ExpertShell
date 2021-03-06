package gui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;


public class NoRun extends Dialog {

	protected Object result;
	protected Shell shlError;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public NoRun(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlError.open();
		shlError.layout();
		Display display = getParent().getDisplay();
		while (!shlError.isDisposed()) {
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
		shlError = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlError.setImage(SWTResourceManager.getImage(NoRun.class, "/resources/exclamation_sign.png"));
		shlError.setSize(365, 150);
		shlError.setText("Error");
		
		Label lblNoKnowledgeBase = new Label(shlError, SWT.WRAP);
		lblNoKnowledgeBase.setLocation(10, 10);
		lblNoKnowledgeBase.setSize(341, 105);
		lblNoKnowledgeBase.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION_TEXT));
		lblNoKnowledgeBase.setText("Unable to Run. Please select a knowledgebase:\r\n\r\nFile >Open KnowledgeBase > KnowledgeBase\r\n");

	}
}
