package Controller;

import Model.Rh;
import View.HumanResourceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HumanResourceController implements ActionListener {
    private Rh humanResourceModel; // Assuming you have a model named Rh
    private HumanResourceView humanResourceView;

    public void setModel(Rh humanResourceModel) {
        this.humanResourceModel = humanResourceModel;
    }

    public void addView(HumanResourceView humanResourceView) {
        this.humanResourceView = humanResourceView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
