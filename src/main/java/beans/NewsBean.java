package beans;

import jakarta.enterprise.inject.New;
import lombok.Data;
import model.News;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "newsBean", eager = true)
@SessionScoped
@Data
public class NewsBean {
    private List<Integer> showNews = new ArrayList<Integer>();
    private News[] news = {new News(1), new News(2), new News(3)};
    private List<News> arr = new ArrayList<News>(Arrays.asList(news));

    public void changeRender(News news) {
        news.setFullTextShow(!news.isFullTextShow());
    }

    public boolean notCanShow(News news) {
        return !showNews.contains(news.getId());
    }

    public boolean canShow(News news) {
        System.out.println(showNews.contains(news.getId()) + " " + news.getId() + " status");
        return showNews.contains(news.getId());
    }

    public String showFullText(News news) {
        if (canShow(news)) {
            return news.getFullText();
        }
        else {
            return "";
        }
    }

    public void accessShowNews(News news) {
        showNews.add(news.getId());
    }
}
