package com.emsi.mbds

class Annonce {

    String title
    String description
    Float price
    Boolean isActive = Boolean.FALSE
    Date dateCreated
    Date lastUpdated
    List illustrations

    static hasMany = [illustrations: Illustration]

    static belongsTo = [author: User]

    static constraints = {
        title blank: false, nullable: false, size: 5..50
        description blank: false, nullable: false
        price nullable: false, scale: 2, min: 0F
        isActive nullable: false
    }

    static mapping = {
        description type: 'text'
    }

}
