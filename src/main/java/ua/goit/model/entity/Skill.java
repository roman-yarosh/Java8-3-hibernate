package ua.goit.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @Column(name = "SKILL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(name = "SKILL_NAME")
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
