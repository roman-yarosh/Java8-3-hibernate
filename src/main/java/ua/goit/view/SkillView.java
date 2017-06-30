package ua.goit.view;

import ua.goit.controller.jdbc.SkillController;
import ua.goit.model.entity.Skill;
import ua.goit.model.entity.SkillFields;

import java.io.IOException;

import static ua.goit.view.ConsoleViewUtils.*;

public class SkillView {

    public void skillViewStarter() throws IOException {

        Skill skill = new Skill();
        SkillController skillController = new SkillController();

        Long skillId;
        String skillName;

        writeMessage("Select CRUD operation: 1 - Create, 2 - Read, 3 - Update, 4 - Delete, 5 - ShowAll, any other - Exit to main menu:");
        int userChoice = readInt();
        switch (userChoice) {
            case CREATE:
                writeMessage(String.format("Enter new %s:", SkillFields.SKILL_NAME.getFieldName()));
                skillName = readString();

                skill.setSkillName(skillName);

                skillController.create(skill);
                writeMessage("Success!");
                break;
            case READ:
                writeMessage(String.format("Enter %s:", SkillFields.SKILL_ID.getFieldName()));
                skillId = readLong();

                writeMessage(skillController.read(skillId).toString());
                writeMessage("Success!");
                break;
            case UPDATE:
                writeMessage(String.format("Enter %s for update:", SkillFields.SKILL_ID.getFieldName()));
                skillId = readLong();
                writeMessage(String.format("Enter new %s:", SkillFields.SKILL_NAME.getFieldName()));
                skillName = readString();

                skill.setSkillId(skillId);
                skill.setSkillName(skillName);

                skillController.update(skill);
                writeMessage("Success!");
                break;
            case DELETE:
                writeMessage(String.format("Enter %s for delete:", SkillFields.SKILL_ID.getFieldName()));
                skillId = readLong();

                skill.setSkillId(skillId);

                skillController.delete(skill);
                writeMessage("Success!");
                break;
            case SHOWALL:
                writeMessage("Skills list:");
                skillController.getAll().forEach(System.out::println);
                writeMessage("Success!");
                break;
            default:
                writeMessage("Exit to main menu!");
                ConsoleViewStarter consoleViewStarter = new ConsoleViewStarter();
                consoleViewStarter.startApp();
                break;
        }
        skillViewStarter();
    }
}
