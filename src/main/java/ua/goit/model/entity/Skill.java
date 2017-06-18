package ua.goit.model.entity;

public class Skill {

    private Long skillId;
    private String skillName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Skill{");
        sb.append("skillId=").append(skillId);
        sb.append(", skillName='").append(skillName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
