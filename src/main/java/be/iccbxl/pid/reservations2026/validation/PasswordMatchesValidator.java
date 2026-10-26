package be.iccbxl.pid.reservations2026.validation;

import be.iccbxl.pid.reservations2026.dto.UserProfileDto;
import be.iccbxl.pid.reservations2026.dto.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        String pwd = null;
        String confirm = null;

        /*
         * DÉVIATION DE LA ROADMAP :
         * La roadmap limite initialement ce validateur à la classe UserRegistrationDto.
         * Cependant, elle exige d'utiliser l'annotation @PasswordMatches sur UserProfileDto
         * lors de l'implémentation de la page de profil. Restreindre le type du validateur
         * à UserRegistrationDto provoque un plantage (ClassCastException) lors de la
         * modification du profil, car Spring tente d'y injecter un objet UserProfileDto.
         *
         * Pour corriger cette erreur technique de conception du document tout en évitant
         * de créer de nouvelles annotations non prévues, nous utilisons 'Object' pour
         * accepter n'importe quel DTO. Nous vérifions ensuite dynamiquement le type
         * (instanceof) pour récupérer les mots de passe correspondants.
         */
        if (obj instanceof UserRegistrationDto) {
            UserRegistrationDto dto = (UserRegistrationDto) obj;
            pwd = dto.getPassword();
            confirm = dto.getConfirmPassword();
        } else if (obj instanceof UserProfileDto) {
            UserProfileDto dto = (UserProfileDto) obj;
            pwd = dto.getPassword();
            confirm = dto.getConfirmPassword();
        } else {
            return true;
        }

        // Si les deux champs sont vides, on considère que le mot de passe n’est pas modifié => OK
        if ((pwd == null || pwd.isBlank()) && (confirm == null || confirm.isBlank())) {
            return true;
        }

        // Sinon, il faut qu’ils soient identiques
        if (pwd == null) return false;
        return pwd.equals(confirm);
    }
}