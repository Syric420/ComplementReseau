/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: syric
 *
 * Created on January 23, 2018, 9:20 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int FctLogin();
void FctEnvoiMail();

/*
 * 
 */
char login[50], email[100];

int main(int argc, char** argv) {
    int repMenuPrinc=0, repMenuSec=0, connecte=0;
    do
    {
        
        printf("Bonjour, que voulez-vous faire ?\n\n");
        printf("1. Se connecter\n");
        printf("2. Quitter\n\n");
        fflush(stdin);
        scanf("%d", &repMenuPrinc);
        system("clear");
        switch(repMenuPrinc)
        {
            case 1:
                connecte = FctLogin();
                break;
            case 2:
                return (EXIT_SUCCESS);
                break;
            default:
                printf("Erreur - vous avez tapé un mauvais chiffre\n");
                break;
                
        }
        fflush(stdout);
        system("clear");
        while(connecte !=0)
        {
            printf("Que voulez-vous faire ?\n\n");
            printf("1. Envoyer un email\n");
            printf("2. Se déconnecter\n");
            printf("3. Quitter\n\n");
            fflush(stdin);
            scanf("%d", &repMenuSec);
            //system("clear");
            switch(repMenuSec)
            {
                case 1:
                    FctEnvoiMail();
                    break;
                case 2:
                    connecte = 0;
                    break;
                case 3:
                    exit(0);
                    break;
                default:
                    printf("Erreur - vous avez tapé un mauvais chiffre\n");
                    break;

            }
            fflush(stdout);
        }
    }while(repMenuPrinc!=2);
    
    return (EXIT_SUCCESS);
}

int FctLogin()
{
    
    system("clear");
    printf("Veuillez entrer votre nom\n\n");
    fflush(stdout);
    fflush(stdin);
    scanf("%s", login);
    system("clear");
    system("telnet 10.59.26.134 25");
    
    system(login);
    system("azerty");
    strcpy(email, login);
    email[strlen(email)]='@';
    strcat(email, "u2.tech.hepl.local");
    return 1;
}

void FctEnvoiMail()
{
    char mailFrom[100], mailTo[100], data[512];
   
    system("EHLO u2.tech.hepl.local");
     //On prepare chaine de carac  mail from
    
    strcpy(mailFrom, "MAIL FROM:");
    mailFrom[strlen(mailFrom)]=0;
    strcat(mailFrom, email);
    //printf("Mail from= %s\n", mailFrom);
    
    //Le mail RCPT TO
    strcpy(mailTo, "RCPT TO:");
    mailFrom[strlen(mailTo)]=0;
    strcat(mailTo, email);
    //printf("mailTo= %s\n", mailTo);
    
    system("DATA");
    system("clear");
    printf("Que voulez vous vous écrire ?\n\n");
    scanf("%s", &data);
    system(data);
    system(".");
    system("QUIT");
    system("clear");
    printf("Message bien envoyé\n\n");
}