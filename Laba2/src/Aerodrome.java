import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Aerodrome {
	ClassArray<ITech> aerodrome;

    int countPlaces = 20;

    int placeSizeWidth = 210;

    int placeSizeHeight = 80;

    public Aerodrome()
    {
        aerodrome = new ClassArray<ITech>(countPlaces, null);
    }

    public int putAircraftInAerodrome(ITech aircraft)
    {
        return aerodrome.add(aerodrome, aircraft);
    }

    public ITech getAircraftInAerodrome(int ticket)
    {
        return aerodrome.dec(aerodrome, ticket);
    }

    private void drawAircraft(Graphics g)
    {
        for (int i = 0; i < countPlaces; i++)
        {
            ITech aircraft = aerodrome.getObject(i);
            if (aircraft != null)
            {
                aircraft.setPosition(70 + i / 5 * placeSizeWidth + 5, i % 5 * placeSizeHeight * 12 / 10 + 80);
                aircraft.drawAircraft(g);
            }
        }
    }
   
    public void drawAerodrome(Graphics g)
    {
    	Graphics2D gr = (Graphics2D)g;
    	BasicStroke pen = new BasicStroke(3);
    	gr.setStroke(pen);

    	gr.setColor(Color.black);
        gr.drawRect(0, 0, (countPlaces / 5) * placeSizeWidth, 490);
        for (int i = 0; i < countPlaces / 5; i++)
        {
            for (int j = 0; j < 6; ++j)
            {
            	gr.setColor(Color.black);
                gr.drawLine(i * placeSizeWidth, j * placeSizeHeight * 12 / 10 + 100, i * placeSizeWidth + 130, j * placeSizeHeight * 12 / 10 + 100);

            	gr.setColor(Color.white);
                gr.drawLine(i * placeSizeWidth, j * placeSizeHeight * 12 / 10 + 70, i * placeSizeWidth + 130, j * placeSizeHeight * 12 / 10 + 70);
                gr.drawLine(i * placeSizeWidth, j * placeSizeHeight * 12 / 10 + 40, i * placeSizeWidth + 130, j * placeSizeHeight * 12 / 10 + 40);
            }
            gr.setColor(Color.black);
            gr.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth, 485);
        }
        
        drawAircraft(g);
    }
}

