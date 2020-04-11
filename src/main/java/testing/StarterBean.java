package testing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@Component("starter")
@SessionScoped
public class StarterBean implements Serializable {
    @Getter private String message = "HELLO!!!";
    @Getter @Setter private String text = "";
    @Getter @Setter private String value = "0";
    private long readyTime;

    public String start() throws IOException {
        System.out.println("Started...");
        System.out.println("text: " + text);
        System.out.println("value: " + value);
        FacesContext.getCurrentInstance().getExternalContext().redirect("waiting.xhtml");
        FacesContext.getCurrentInstance().responseComplete();
        readyTime = System.currentTimeMillis() + 1000 * 5; // 5 seconds after call
        return "OK";
    }

    public void checkready() throws IOException {
        System.out.println("Readiness check...");
        if (readyTime < System.currentTimeMillis()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("result.xhtml");
            FacesContext.getCurrentInstance().responseComplete();
        }
    }


}
