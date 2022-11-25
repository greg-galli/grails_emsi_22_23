package mbds_emsii_22_23

import com.emsi.mbds.Annonce
import com.emsi.mbds.Illustration
import com.emsi.mbds.Role
import com.emsi.mbds.User
import com.emsi.mbds.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def clientRole = new Role(authority: 'ROLE_CLIENT').save()
        def adminUser = new User(username: 'admin', password: 'admin').save()
        UserRole.create(adminUser, adminRole, true)

        ["Alice", "Bob", "Charly", "Denis", "Etienne"].each {
            String uName ->
                def userInstance = new User(username: uName, password: "password")
                (1..5).each {
                    Integer aIndex ->
                        def annonceInstance = new Annonce(
                                title: "Titre $uName $aIndex",
                                description: "Description de l'annonce pour $uName, avec l'index $aIndex",
                                price: 100 * aIndex,
                                isActive: Boolean.TRUE)
                        (1..5).each {
                            annonceInstance.addToIllustrations(new Illustration(filename: 'grails.svg'))
                        }
                        userInstance.addToAnnonces(annonceInstance)
                }
                userInstance.save(flush: true, failOnError: true)
                UserRole.create(userInstance, clientRole, true)
        }
    }
    def destroy = {
    }
}
