package org.motechproject.ebodac.domain;

import org.apache.commons.lang.StringUtils;
import org.motechproject.ebodac.constants.EbodacConstants;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.annotations.Ignore;
import org.motechproject.mds.annotations.NonEditable;
import org.motechproject.mds.annotations.UIDisplayable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Unique;

/**
 * Models data for registration of Subject in EBODAC
 */
@Entity(recordHistory = true)
public class Subject {

    /**
     *  Fields captured in ZETES
     */

    private Long id;

    @Unique
    @NonEditable
    @UIDisplayable(position = 0)
    @Field(required = true)
    private String subjectId;

    @UIDisplayable(position = 1)
    @Field
    private String name;

    @UIDisplayable(position = 2)
    @Field
    private String householdName;

    @UIDisplayable(position = 3)
    @Field
    private String headOfHousehold;

    @UIDisplayable(position = 4)
    @Column(length = 20)
    @Field
    private String phoneNumber;

    @UIDisplayable(position = 5)
    @Field
    private String address;

    @UIDisplayable(position = 7)
    @Column(length = 20)
    @Field
    private Language language;

    @NonEditable
    @UIDisplayable(position = 8)
    @Field(required = true)
    private String siteId;

    @UIDisplayable(position = 9)
    @Field
    private String community;

    /**
     *  Fields captured in RAVE
     */

    @UIDisplayable(position = 6)
    @Field
    private Gender gender;

    /**
     *  Motech internal fields
     */
    @NonEditable(display = false)
    @Field(defaultValue = "false")
    private boolean changed;

    @NonEditable(display = false)
    @Field
    private String owner;

    public Subject() {
    }

    public Subject(String subjectId, String name, String householdName, String headOfHousehold,
                   String phoneNumber, String address, Language language, String community, String siteId) {
        this.subjectId = subjectId;
        this.name = name;
        this.householdName = householdName;
        this.headOfHousehold = headOfHousehold;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.language = language;
        this.community = community;
        setSiteId(siteId);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        if (StringUtils.isBlank(siteId)) {
            this.siteId = EbodacConstants.SITE_ID_FOR_STAGE_I;
        } else {
            this.siteId = siteId;
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getHeadOfHousehold() {
        return headOfHousehold;
    }

    public void setHeadOfHousehold(String headOfHousehold) {
        this.headOfHousehold = headOfHousehold;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Ignore
    public String getLanguageCode() {
        if (language != null) {
            return language.getCode();
        } else {
            return null;
        }

    }

    @Override
    public String toString() {
        return String.format("Subject{name='%s', householdName='%s', phoneNumber='%s'}",
                getName(), getHouseholdName(), getPhoneNumber());
    }
}