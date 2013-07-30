package com.teraim.strand;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySubstratSelection extends Activity {

	GridView gridView;

	final Provyta py = Strand.getCurrentProvyta(this);

	static final String[] zones = {"Hydro","Geo","Supra","Extra"};

	static final String[] fields = new String[] { 
		"", "HL", "GL", "SL", "XL",
		"Organiskt", "G", "H", "I", "J",
		"Lera", "L", "M", "N", "O",
		"Sand", "Q", "R", "S", "T",
		"Block", "V", "W", "X", "Y",
		"SUM:","","","",""};

	private int col=-1,sum=-1; 

	//need a flag to keep track of timers..
	private boolean timed = false;
	private Timer timer;


	static final int NO_OF_COLS  = 5;
	static final int NO_OF_ROWS  = 5;
	
	static final int LENGTH_OF_COLS  = 6;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.substrat_table);
		gridView = (GridView) findViewById(R.id.grid);


		//Are there values already? 
		//If so, extract & put into fields.

		String[][] values = py.getSubstrat();
		//If no values, generate.
		if (values==null) {
			values = new String[NO_OF_COLS-1][LENGTH_OF_COLS-2];
			String[] row;
			for (int c=0;c<(NO_OF_COLS-1);c++) {
				row = values[c];
				for (int j=0;j<(LENGTH_OF_COLS-2);j++)
					row[j] = "["+c+","+j+"]";
			}
			py.setSubstrat(values);
		}
		String[] row;
		for (int c=0;c<(NO_OF_COLS-1);c++) {
			row = values[c];
			for (int j=0;j<(LENGTH_OF_COLS-2);j++)
				fields[c+1+NO_OF_COLS*(j+1)]=row[j];
		}


		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, fields);

		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, final View v,
					final int position, long id) {
				//check if col = 0. If so - exit, since it is not part of the clickables..
				col = position%NO_OF_COLS;
				if (col==0) 
					return;				
				Toast.makeText(getApplicationContext(),
						((TextView) v).getText(), Toast.LENGTH_SHORT).show();
				//On click, create dialog 			
				AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
				alert.setTitle(zones[col-1]);
				alert.setMessage("V�rdet i kolumnerna skall sammanlagt uppn� 100");
				final LinearLayout et = (LinearLayout)LayoutInflater.from(ActivitySubstratSelection.this).inflate(R.layout.edit_text, null);
				alert.setView(et);
				final TextView summa = (TextView)et.findViewById(R.id.sum);
				final int sb[] = {R.id.sb1,R.id.sb2,R.id.sb3,R.id.sb4};
				final SeekBar SB[] = new SeekBar[4];

				int i=0;
				for(int ide:sb) 
					SB[i++]=(SeekBar)et.findViewById(ide);

				final SparseIntArray currSBValue = new SparseIntArray();

				class UpdateBarsTask extends TimerTask {
					int[] targetValues=new int[currSBValue.size()];

					public void run() {
						//Log.d("Strand","Gets here");
						boolean notDone = true;
						while (notDone) {
							float percent=0;
							int total = 0;
							for(int s=0;s<currSBValue.size();s++) {
								int val=currSBValue.valueAt(s);
								percent = (float)val/sum;
								//Log.d("Strand","Targetvalues updatedd");
								targetValues[s] = (int) Math.round(percent*100);
								total += targetValues[s];
							}
							//If sum not 100 because of rounding errors, find the biggest value and increase/decrease.
							if (total != 100) {
								int max = -1; int pos = -1; int x=0;
								for (int i:targetValues) {
									if (i>max) {
										max = i;
										pos = x;
									}
									x++;
								}
								//remove the diff.
								int difference = 100-total;
								targetValues[pos] += difference;
								//Log.d("Strand","Targetvalues updated");
							}

							notDone = false;					
							for(int s=0;s<currSBValue.size();s++) {
								int val=currSBValue.valueAt(s);
								int key=currSBValue.keyAt(s);
								int target = targetValues[s];
								//Log.d("Strand","Current value: "+val+" S: "+s+" Target value: "+targetValues[s]);

								//keep looping until all values are at target.
								if (val<target) {
									val += 1;
									notDone = true;
								}
								else if (val>target) {
									val -= 1;
									notDone = true;
								}
								//update seekbar to reflect the new value
								SB[s].setProgress(val);
								currSBValue.put(key, val);
								if (!timed)
									notDone=false;
							}
							if (timed) {
								try {
									synchronized (this) {
										this.wait(50);
									}
								} catch (InterruptedException e) {
									Log.e("Strand","Wait interrupted for UpdateBarTask!");
								}
							}
						}
						
						timed = false;
						Log.e("Strand","Seekbars reached 100");
					}
				}

				for (final SeekBar seekbar:SB) {
					currSBValue.put(seekbar.getId(),0);
					seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
					{
						private long INITIAL_DELAY = 1500;
						public void onProgressChanged(SeekBar seekBar, int progress,
								boolean fromUser)
						{
							currSBValue.put(seekbar.getId(), progress);
							updateSumma();

							if (!timed) {
								timer = new Timer();
								timer.schedule(new UpdateBarsTask(), INITIAL_DELAY );
								timed = true;

							}
						}

						private void updateSumma() {
							sum = 0;
							for(int s=0;s<=currSBValue.size();s++) 
								sum+=currSBValue.valueAt(s);

							summa.setText("Summa: "+sum);
						}

						public void onStartTrackingTouch(SeekBar seekBar)
						{
							Log.d("Strand","Reset and stop timer");
							if (timed) {
								timer.cancel();
								timed = false;
							}
						}

						public void onStopTrackingTouch(SeekBar seekBar)
						{
							// TODO Auto-generated method stub
						}
					});
				}
				alert.setPositiveButton("Spara", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {				  
						//((TextView) v).setText(et.getText());
						int elem = -1;
						//Iterate through all rows
						for(int i=1;i<=4;i++) {
							elem = col+i*NO_OF_COLS;
							if (elem > fields.length)
								Log.e("Strand","Elem out of bounds: "+elem);
							else {
								Log.e("Strand","Field : Elem : SBValue : "+fields[elem]+" "+elem+" "+currSBValue.valueAt(i-1));
								int value = currSBValue.valueAt(i-1);
								fields[elem]=Integer.toString(value);
							}
						}
						//update sum.
						String v = Integer.toString(sum);
//						String z = (sum!=100)?"<font fgcolor=\"#ffff0000\">"+v+"</font>":"<font fgcolor=\"#00000000\">"+v+"</font>";
						fields[elem+NO_OF_COLS] = v;
						adapter.notifyDataSetChanged();
						
						String[][] su = py.getSubstrat();
						for(int i=0;i<NO_OF_COLS-1;i++)
							for(int j=0;j<NO_OF_ROWS-1;j++)
						su[i][j]=fields[1+i+(j+1)*NO_OF_COLS];
								
						py.setSubstrat(su);
					}

				});
				alert.setNegativeButton("Avbryt", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});	
				alert.show();
			}
		});
	}


}
