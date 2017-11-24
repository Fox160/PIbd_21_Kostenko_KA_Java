import java.awt.Color;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DropLabelColor extends JLabel implements DropTargetListener {

	public DropLabelColor(String text) {
		super(text);
		new DropTarget(this, this);
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		if (dtde.getTransferable().isDataFlavorSupported(DataColors.colorFlavor)) {
			dtde.acceptDrag(DnDConstants.ACTION_COPY);
		} else {
			dtde.acceptDrag(DnDConstants.ACTION_NONE);
		}
	}

	@Override
	public void dragExit(DropTargetEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOver(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		try {
			Transferable transferable = dtde.getTransferable();
			if (transferable.isDataFlavorSupported(DataColors.colorFlavor)) {
				Color color = (Color) transferable.getTransferData(DataColors.colorFlavor);
				setBackground(color);
				ITech aircraft = ((DropAircraftPanel) getLabelFor()).getAircraft();
				if (aircraft != null)
					if (getText() == "Основной цвет") {
						aircraft.setMainColor(color);
					} else if (getText() == "Доп. цвет") {
						if (aircraft instanceof FighterAircraft) {
							((FighterAircraft) aircraft).setDopColor(color);
						}
					}
				if (getLabelFor() instanceof JPanel) {
					((JPanel) getLabelFor()).repaint();
				}

			} else {
				dtde.rejectDrop();
			}
		} catch (Exception e) {
			dtde.rejectDrop();
		}
	}
}
