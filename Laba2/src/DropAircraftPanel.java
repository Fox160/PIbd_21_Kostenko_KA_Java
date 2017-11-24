import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

import javax.swing.JPanel;

public class DropAircraftPanel extends JPanel implements DropTargetListener {

	private ITech aircraft;

	public DropAircraftPanel() {
		new DropTarget(this, this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (aircraft != null) {
			aircraft.drawAircraft(g);
		}
	}

	public ITech getAircraft() {
		return aircraft;
	}

	public void setAircraft(ITech aircraft) {
		this.aircraft = aircraft;
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		if (dtde.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)) {
			dtde.acceptDrag(DnDConstants.ACTION_COPY);
		} else {
			dtde.acceptDrag(DnDConstants.ACTION_NONE);
		}

	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable transferable = dtde.getTransferable();
			if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				switch ((String) transferable.getTransferData(DataFlavor.stringFlavor)) {
				case "Самолёт":
					aircraft = new CivillianAircraft(200, 5, 100, Color.BLUE);
					break;
				case "Истребитель":
					aircraft = new FighterAircraft(200, 5, 100, Color.BLUE, true, true, true, Color.WHITE, 4);
					break;
				}

				repaint();
			} else {
				dtde.rejectDrop();
			}
		} catch (Exception e) {
			dtde.rejectDrop();
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

}
