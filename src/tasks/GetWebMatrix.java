package tasks;


import api.HandleWeb;
import framework.Task;
import handling.website.Web;
import data.Vars;
import handling.website.enums.ModeType;

/**
 * Created by Sphiinx on 4/13/2016.
 */
public class GetWebMatrix implements Task {

    public void execute() {
        if (!Web.isGameOpen("//*[@id=\"game\"]"))
            return;

        Web.resetGame(ModeType.BEGINNER.getModexPath());
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (!HandleWeb.getElementXPath(HandleWeb.driver, "//*[@id=\"table\"]/tbody/tr[" + i + "]/td[" + j + "]/div").getText().contains(" ")) {
                    Vars.get().matrix[i - 1][j - 1] = Integer.parseInt(HandleWeb.getElementXPath(HandleWeb.driver, "//*[@id=\"table\"]/tbody/tr[" + i + "]/td[" + j + "]/div").getText());
                }
            }
        }
    }

    public boolean validate() {
        int cells = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Vars.get().matrix[i][j] == 0) {
                    cells++;
                }
            }
        }
        return cells == 81;
    }
}

