## **Claudion**

![claudion_screenshot](https://user-images.githubusercontent.com/30610148/97768692-14ddb900-1b79-11eb-8e49-d1ee8eabcce2.png)

Claudion is a web-based game heavily inspired by Sigmar's Garden, a minigame from the very fun [Opus Magnum](http://www.zachtronics.com/opus-magnum/) by Zachtronics.

The objective is to clear all stones off the board. 

There are essentially three types of stone:

<img src="public/assets/app/images/stone.svg" width="50" height="50" style="display: inline"> Coloured stones like these can only be matched against their corresponding colour. The exception is the grey stone which functions as a wildcard and can match on all other colours, including other wildcards.



<img src="public/assets/app/images/potion.svg" width="50" height="50" style="display: inline"><img src="public/assets/app/images/wealth.svg" width="50" height="50" style="display: inline;filter: grayscale(70%)"><img src="public/assets/app/images/gold.svg" width="50" height="50" style="display: inline"> Wealth stones need to be removed in the order with which they can be transmuted via potions. When all lesser metals are removed, the gold can be removed just by clicking on it. 

The order they need to be removed is as follows:

<img src="public/assets/app/images/wealth.svg" width="50" height="50" style="display: inline;filter: grayscale(70%)"> Tin

<img src="public/assets/app/images/wealth.svg" width="50" height="50" style="display: inline;filter: hue-rotate(35deg) grayscale(70%)"> Iron

<img src="public/assets/app/images/wealth.svg" width="50" height="50" style="display: inline;filter: hue-rotate(260deg)"> Garnet

<img src="public/assets/app/images/wealth.svg" width="50" height="50" style="display: inline;filter: hue-rotate(65deg)"> Jade

<img src="public/assets/app/images/wealth.svg" width="50" height="50" style="display: inline;filter: hue-rotate(140deg)"> Aquamarine



<img src="public/assets/app/images/pale_dual.svg" width="50" height="50" style="display: inline"><img src="public/assets/app/images/vibrant_dual.svg" width="50" height="50" style="display: inline"> Life stones can be removed in pairs by matching each opposing stone.



Built and compiled with [shadow-cljs](https://shadow-cljs.org/).