package com.mycompany.db_emulador;
import java_cup.runtime.*;


%%


/* Declaraciones */
%public
%class Lexico
%cup
%line
%column

logic=AND|OR
esp=[ \t\r\n\f]
selec=SELECCIONAR
filt=FILTRAR
insertar=INSERTAR
eliminar=ELIMINAR
actu=ACTUALIZAR
asig=ASIGNAR
en=EN
valores=VALORES
dot=\.
coma=,
extcsv=\c\s\v
path=id dot extcsv
para=\(
parc=\)
fin_linea=\;
relac=\=|<|>|<=|>=|<>
numero=[0-9][0-9]*
id=[a-zA-Z][a-zA-Z0-9]*
string=[0-9a-zA-Z]+
valor=\"string\"|numero


%%


{esp} 	   {/* no hace nada */}
{selec}    { return new Symbol(sym.SELEC, yyline+1, yycolumn+1, new String(yytext()));}
{filt}     { return new Symbol(sym.FILT, yyline+1, yycolumn+1, new String(yytext()));}
{insertar} { return new Symbol(sym.INSERTAR, yyline+1, yycolumn+1, new String(yytext()));}
{eliminar} { return new Symbol(sym.ELIMINAR, yyline+1, yycolumn+1, new String(yytext()));}
{actu}     { return new Symbol(sym.ACTUALIZAR, yyline+1, yycolumn+1, new String(yytext()));}
{asig}     { return new Symbol(sym.ASIGNAR, yyline+1, yycolumn+1, new String(yytext()));}
{en}       { return new Symbol(sym.EN, yyline+1, yycolumn+1, new String(yytext()));}
{valores}  { return new Symbol(sym.VALORES, yyline+1, yycolumn+1, new String(yytext()));}
{dot}      { return new Symbol(sym.DOT, yyline+1, yycolumn+1, new String(yytext()));}
{coma}     { return new Symbol(sym.COMA, yyline+1, yycolumn+1, new String(yytext()));}
{extcsv}   { return new Symbol(sym.CSV, yyline+1, yycolumn+1, new String(yytext()));}
{path}     { return new Symbol(sym.PATH, yyline+1, yycolumn+1, new String(yytext()));}
{valor}    { return new Symbol(sym.VALOR, yyline+1, yycolumn+1, new String(yytext()));}
{para}     { return new Symbol(sym.PARA, yyline+1, yycolumn+1, new String(yytext()));}
{parc}     { return new Symbol(sym.PARC, yyline+1, yycolumn+1, new String(yytext()));}
{fin_linea} { return new Symbol(sym.FINL, yyline+1, yycolumn+1, new String(yytext()));}
{logic}    { return new Symbol(sym.LOGIC, yyline+1, yycolumn+1, new String(yytext()));}
{relac}    { return new Symbol(sym.RELAC, yyline+1, yycolumn+1, new String(yytext()));}
{numero}   { return new Symbol(sym.NUMERO, yyline+1, yycolumn+1, new String(yytext()));}
{id}       { return new Symbol(sym.ID, yyline+1, yycolumn+1, new String(yytext()));}
{string}   { return new Symbol(sym.STRING, yyline+1, yycolumn+1, new String(yytext()));}

.          {  System.out.println("No se reconocio este simbolo: " + yytext());}  // return new Symbol(sym.ERROR, yyline+1, yycolumn+1, new String(yytext()))  ;