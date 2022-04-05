package com.mycompany.webdeneme;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLInputElement;

public class FXMLController implements Initializable {

    @FXML
    private WebView webView;

    @FXML
    private CheckBox CHSinirsizIslem;

    @FXML
    private CheckBox CHXislem;

    @FXML
    private CheckBox CHIslemSonrası;

    @FXML
    private Label islemLBL;

    @FXML
    private Label molaLBL;

    @FXML
    private Label islemSayisiLBL;

    @FXML
    private RadioButton RDMAbone;

    @FXML
    private RadioButton RDMTakipKazan;

    @FXML
    private RadioButton RDMKazanYoutube;

    @FXML
    private RadioButton RDMLinkCollider;

    @FXML
    private Button BtnBaslat;

    @FXML
    private Button BtnDurdur;

    @FXML
    private TextField TxtMolaSuresi;

    @FXML
    private TextField TxtIslemSirasi;

    @FXML
    private Button BtnZamanlPcKapat;

    @FXML
    private Button BtnKapatmaDurdur;

    @FXML
    private TextField TxtKapatmaSuresi;

    public WebEngine webEngine = null;
    int deger = 0;
    int islem = 0;
    int hangiSite = 0;
//   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();
        webEngine.load("https://google.com/");

        webEngine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

            @Override
            public WebEngine call(PopupFeatures p) {
                Stage stage = new Stage(StageStyle.UTILITY);
                WebView wv2 = new WebView();
                stage.setScene(new Scene(wv2));
                stage.close();
                wv2.isDisable();
                return wv2.getEngine();
            }
        });

    }

    void popup() {
        webEngine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

            @Override
            public WebEngine call(PopupFeatures p) {
                Stage stage = new Stage(StageStyle.UTILITY);
                WebView wv2 = new WebView();
                stage.setScene(new Scene(wv2));
                stage.show();
                wv2.isDisable();
                return wv2.getEngine();
            }
        });

    }

    @FXML
    private void BtnDurdurClick(ActionEvent event) {
        webEngine.executeScript("$('.btn-u')[7].click()");
    }

    @FXML
    private void baslatClick(ActionEvent event) {
        try {
            if (RDMAbone.isSelected() == false && RDMKazanYoutube.isSelected() == false && RDMTakipKazan.isSelected() == false && RDMLinkCollider.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "Bir site seçmeniz gerekmektedir !");
            } else {
                deger = 0;
                if (RDMAbone.isSelected() == true) {
                    aboneCanliTMR();
                }
                if (RDMTakipKazan.isSelected() == true) {
                    takipKazanTMR();
                }
                if (RDMKazanYoutube.isSelected() == true) {
                    kazanYoutubeTMR();
                }
                if (RDMLinkCollider.isSelected() == true) {
                    linkColliderTMR();
                }
            }

            //webEngine.executeScript("javascript:var s = function() {window.setInterval(\"YouTubePlaying()\", 100)}; s();");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void RDMAboneClick(ActionEvent event) {
        RDMAboneClickFNC();
    }

    private void RDMAboneClickFNC() {
        deger = 0;
        webEngine.load("https://abone.canliabonesayaci.com/index.php");
        hangiSite = 1;
        girisTMR();
    }

    @FXML
    private void RDMTakipKazanClick(ActionEvent event) {
        RDMTakipKazanClickFNC();
    }

    private void RDMTakipKazanClickFNC() {
        deger = 0;
        webEngine.load("https://takipkazan.net/");
        hangiSite = 2;
        girisTMR();
    }

    @FXML
    private void RDMKazanYoutubeClick(ActionEvent event) {
        RDMKazanYoutubeClickFNC();
    }

    private void RDMKazanYoutubeClickFNC() {
        deger = 0;
        webEngine.load("https://kazan.youtubeturkiye.net/");
        hangiSite = 3;
        girisTMR();
    }

    @FXML
    private void RDMLinkColliderClick(ActionEvent event) {
        RDMLinkColliderClickFNC();
    }

    private void RDMLinkColliderClickFNC() {
        deger = 0;
        webEngine.load("https://www.linkcollider.com/page/login");
        hangiSite = 4;
        girisTMR();
    }

    private void girisTMR() {
        final Timer myTimer = new Timer();
        TimerTask gorev = new TimerTask() {

            @Override
            public void run() {
                deger++;

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        BtnBaslat.setDisable(true);
                        BtnDurdur.setDisable(true);
                        islemLBL.setText("" + deger);
                        if (deger == 10) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    girisYap();
                                    if (CHSinirsizIslem.isSelected() == true) {
                                        if (hangiSite == 1) {
                                            deger = 0;
                                            aboneCanliTMR();
                                            myTimer.cancel();
                                            BtnBaslat.setDisable(false);
                                            BtnDurdur.setDisable(false);
                                        }
                                        if (hangiSite == 2) {
                                            deger = 0;
                                            takipKazanTMR();
                                            myTimer.cancel();
                                            BtnBaslat.setDisable(false);
                                            BtnDurdur.setDisable(false);
                                        }
                                        if (hangiSite == 3) {
                                            deger = 0;
                                            kazanYoutubeTMR();
                                            myTimer.cancel();
                                            BtnBaslat.setDisable(false);
                                            BtnDurdur.setDisable(false);
                                        }
                                        if (hangiSite == 4) {
                                            deger = 0;
                                            linkColliderTMR();
                                            myTimer.cancel();
                                            BtnBaslat.setDisable(false);
                                            BtnDurdur.setDisable(false);
                                        }
                                    }
                                }

                            });
                        }
                        if (deger > 11) {
                            BtnBaslat.setDisable(false);
                            BtnDurdur.setDisable(false);
                            myTimer.cancel();
                        }

                    }

                });
            }
        };
        myTimer.schedule(gorev, 0, 1300);
    }

    private void girisYap() {
        try {
            if (RDMAbone.isSelected() == true) {
                String email = "document.getElementsByName('login')[0].value='username';";
                String pass = "document.getElementsByName('pass')[0].value='pw';";
                webEngine.executeScript(email);
                webEngine.executeScript(pass);
                webEngine.executeScript("$('.btn')[0].click()");
            }
            if (RDMTakipKazan.isSelected() == true) {
                String email = "document.getElementsByName('login')[0].value='username';";
                String pass = "document.getElementsByName('pass')[0].value='pw';";
                webEngine.executeScript(email);
                webEngine.executeScript(pass);
                webEngine.executeScript("$('.button')[0].click()");
            }
            if (RDMKazanYoutube.isSelected() == true) {
                String email = "document.getElementsByName('login')[0].value='username';";
                String pass = "document.getElementsByName('pass')[0].value='pw';";
                webEngine.executeScript(email);
                webEngine.executeScript(pass);
                webEngine.executeScript("$('.btn')[0].click()");
            }
            if (RDMLinkCollider.isSelected() == true) {
                String email = "document.getElementsByName('email')[0].value='username';";
                String pass = "document.getElementsByName('pw')[0].value='pw';";
                webEngine.executeScript(email);
                webEngine.executeScript(pass);
                webEngine.executeScript("$('.btn-u')[7].click()");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void aboneCanliTMR() {
        final Timer aboneCanli = new Timer();
        TimerTask aboneCanliTMRgorev = new TimerTask() {

            @Override
            public void run() {
                deger++;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        islemLBL.setText("" + deger);
                        if (islem >= Integer.parseInt(TxtIslemSirasi.getText()) && CHXislem.isSelected() == true) {
                            deger = 0;
                            islem = 0;
                            islemLBL.setText("0");
                            RDMTakipKazan.setSelected(true);
                            RDMTakipKazanClickFNC();
                            aboneCanli.cancel();
                        } else {
                            if (deger == 5) {
                                webEngine.load("https://abone.canliabonesayaci.com/p.php?p=youtube");
                            }
                            if (deger == 12) {
                                int durum = videoIzleClick();
                                if (durum == 1) {
                                    if (CHSinirsizIslem.isSelected() == true) {
                                        RDMTakipKazan.setSelected(true);
                                        RDMTakipKazanClickFNC();
                                        deger = 0;
                                        islem = 0;
                                        islemLBL.setText("0");
                                        aboneCanli.cancel();
                                    }
                                }
                            }
                            if (deger == 20) {
                                playButonClick();
                            }
                            if (deger == 30) {
                                deger = 0;
                                islem++;
                                islemSayisiLBL.setText(islem + " ");
                            }
                        }
                    }
                });
            }
        };
        aboneCanli.schedule(aboneCanliTMRgorev, 0, 1300);
    }

    private void takipKazanTMR() {
        final Timer myTimer = new Timer();
        TimerTask gorev = new TimerTask() {

            @Override
            public void run() {
                deger++;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        islemLBL.setText("" + deger);
                        if (islem >= Integer.parseInt(TxtIslemSirasi.getText()) && CHXislem.isSelected() == true) {
                            deger = 0;
                            islem = 0;
                            islemLBL.setText("0");
                            RDMKazanYoutube.setSelected(true);
                            RDMKazanYoutubeClickFNC();
                            myTimer.cancel();
                        } else {
                            if (deger == 5) {
                                webEngine.load("https://takipkazan.net/p.php?p=youtube");
                            }
                            if (deger == 12) {
                                int durum = videoIzleClick();
                                if (durum == 1) {
                                    if (CHSinirsizIslem.isSelected() == true) {
                                        RDMKazanYoutube.setSelected(true);
                                        RDMKazanYoutubeClickFNC();
                                        deger = 0;
                                        islem = 0;
                                        islemLBL.setText("0");
                                        myTimer.cancel();
                                    }
                                }
                            }
                            if (deger == 20) {
                                playButonClick();
                            }
                            if (deger == 30) {
                                deger = 0;
                                islem++;
                                islemSayisiLBL.setText(islem + " ");
                            }
                        }
                    }
                });
            }
        };
        myTimer.schedule(gorev, 0, 1300);
    }

    private void kazanYoutubeTMR() {
        final Timer myTimer = new Timer();
        TimerTask gorev = new TimerTask() {

            @Override
            public void run() {
                deger++;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        islemLBL.setText("" + deger);

                        if (islem >= Integer.parseInt(TxtIslemSirasi.getText()) && CHXislem.isSelected() == true) {
                            RDMLinkCollider.setSelected(true);
                            RDMLinkColliderClickFNC();
                            deger = 0;
                            islem = 0;
                            islemLBL.setText("0");
                            myTimer.cancel();
                        } else {
                            if (deger == 5) {
                                webEngine.load("https://kazan.youtubeturkiye.net/p.php?p=youtube");
                            }
                            if (deger == 12) {
                                int durum = videoIzleClick();
                                if (durum == 1) {
                                    if (CHSinirsizIslem.isSelected() == true) {
                                        RDMLinkCollider.setSelected(true);
                                        RDMLinkColliderClickFNC();
                                        deger = 0;
                                        islem = 0;
                                        islemLBL.setText("0");
                                        myTimer.cancel();
                                    }
                                }
                            }
                            if (deger == 20) {
                                playButonClick();
                            }
                            if (deger == 30) {
                                deger = 0;
                                islem++;
                                islemSayisiLBL.setText(islem + " ");
                            }
                        }

                    }
                });
            }
        };
        myTimer.schedule(gorev, 0, 1300);
    }

    private void linkColliderTMR() {
        final Timer myTimer = new Timer();
        TimerTask gorev = new TimerTask() {

            @Override
            public void run() {
                deger++;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        islemLBL.setText("" + deger);

                        if (islem >= Integer.parseInt(TxtIslemSirasi.getText()) && CHXislem.isSelected() == true) {
                            deger = 0;
                            islem = 0;
                            islemLBL.setText("0");
                            molaTMR();
                            myTimer.cancel();
                        } else {
                            if (deger == 5) {
                                webEngine.load("https://www.linkcollider.com/page/activity/clicks");
                            }
                            if (deger == 15) {
                                int durum = surfClick();
                                if (durum == 1) {
                                    if (CHSinirsizIslem.isSelected() == true) {
                                        deger = 0;
                                        islem = 0;
                                        islemLBL.setText("0");
                                        molaTMR();
                                        myTimer.cancel();
                                    }
                                }
                            }
                            if (deger == 7) {
//                                playButonClick(); play yok bunda
                            }
                            if (deger == 60) {
                                deger = 0;
                                islem++;
                                islemSayisiLBL.setText(islem + " ");
                            }
                        }

                    }
                });
            }
        };
        myTimer.schedule(gorev, 0, 1300);
    }

    private int videoIzleClick() {
        try {
            webEngine.executeScript("document.querySelector(\".followbutton\").click()");
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    private int surfClick() {
        try {
//            webEngine.executeScript("document.querySelector(\".btn-u\").click()");
            webEngine.executeScript("$('.btn-u')[7].click()");
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    private void playButonClick() {
        webEngine.executeScript("javascript:var s = function() {window.setInterval(\"YouTubePlaying()\", 1)}; s();");
    }

    private void molaTMR() {
        final Timer molaTMR = new Timer();
        TimerTask molaTMRgorev = new TimerTask() {

            @Override
            public void run() {
                deger++;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        islemLBL.setText("" + deger);
                        int molaSuresi = Integer.parseInt(TxtMolaSuresi.getText()) * 60;
                        molaLBL.setText(molaSuresi + "");
                        if (deger > molaSuresi) {
                            RDMAbone.setSelected(true);
                            RDMAboneClickFNC();
                            deger = 0;
                            islem = 0;
                            molaTMR.cancel();
                        }
                    }
                });
            }
        };
        molaTMR.schedule(molaTMRgorev, 0, 1000);
    }
}
