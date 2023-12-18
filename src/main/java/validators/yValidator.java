package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("yValidator")
public class yValidator implements Validator {
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        try {
            Double newValue = (Double) value;
            if (value.equals("") || (value.toString()).length() > 10 || newValue < -5 || newValue > 5) {
                throw new Exception();
            }
        } catch (Exception e) {
            FacesMessage msg =
                    new FacesMessage("Invalid input");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}