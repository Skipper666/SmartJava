package steam.pages;

import smartframework.base.BasePage;
import smartframework.elements.Label;
import smartframework.utils.FileWorker;


public class DownloadPage extends BasePage {

    private Label btnDownload = new Label(pageNoMenuLocator);

    public DownloadPage() {
        super(pageNoMenuLocator);
    }

    public void download() {
        btnDownload.click();
        log.info("Ожидаем скачивания файла");
        while (!FileWorker.checkExistFile()) {
        }
        log.info("Файл успешно скачан");
    }
}