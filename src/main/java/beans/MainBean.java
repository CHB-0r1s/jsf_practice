package beans;


import lombok.Data;
import model.DB;
import model.Point;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;




@ManagedBean(name = "mainBean", eager = true)
@SessionScoped
@Data
public class MainBean {

    private Point newData;
    private String shape_name;
    private List<Point> dataList = new ArrayList<Point>();

    public String getShape_name() {
        return shape_name;
    }

    public void addData() {
        newData.setStatus(false);
        dataList.add(newData);
        newData = new Point();
    }

    public Point getNewData() {
        if (this.newData == null) {
            this.newData = new Point(.0, .0, 2.0, false);
        }
        return newData;
    }

    public String updateData() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Point prev = this.newData;
        this.newData = new Point(Double.parseDouble(request.getParameter("form2:x1")), Double.parseDouble(request.getParameter("form2:y1")), Double.parseDouble(request.getParameter("form2:r1")), false);
        createData();
        this.newData = prev;
        return "redirectURL";
    }

    public void setNewData(Point newData) {
        this.newData = newData;
    }

    public List<Point> getDataList() {
        if (newData != null && newData.getR() != null) {
            DB DBInstance = DB.getInstance();
            return DBInstance.getAll(newData.getR());
        }
        return new ArrayList<Point>();
    }

    public void setDataList(List<Point> dataList) {
        this.dataList = dataList;
    }

    public void clear() {
        dataList.clear();
    }

    public void createData() {
        DB DBInstance = DB.getInstance();
        DBInstance.add(this.newData);
    }

    public void clearData() {
        DB DBInstance = DB.getInstance();
        DBInstance.clear();
    }

}