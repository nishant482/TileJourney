import{extraBackgrounds}from './extra-backgrounds.mjs';import{collectionDefs}from './collections.mjs';
export const catalog=[
['coast','Seaside Castle','coast','#00486500','none'],
['forest','Rainforest','forest','#123d1700','none'],
['city','Cherry City','city','#d9518400','none'],
['sunset','Golden Sunset','coast','#ff7c4455','sepia(.18) saturate(1.18)'],
['moon','Moonlight Garden','city','#101b5b9c','brightness(.7) saturate(.85)'],
['alpine','Alpine Village','alpine','#18639900','none'],
['winter','Winter Wonderland','alpine','#b0e4ff35','brightness(1.08) saturate(.8)'],
['aurora','Aurora Dreams','alpine','#43137b90','brightness(.76) hue-rotate(12deg)'],
['desert','Desert Oasis','desert','#a2551900','none'],
['oasis','Emerald Oasis','desert','#0c936631','saturate(1.25)'],
['desert-dusk','Sands at Dusk','desert','#d9435b66','brightness(.84) sepia(.15)'],
['lagoon','Turquoise Lagoon','beach','#1f9ca400','none'],
['coral','Coral Coast','beach','#fa748230','saturate(1.12)'],
['tropical','Tropical Paradise','beach','#85c20e20','saturate(1.3)'],
['island-night','Island Moonlight','beach','#15216e99','brightness(.7)'],
['lavender','Lavender Fields','meadow','#8c39c600','none'],
['rose-garden','Rose Garden','meadow','#ed678c28','saturate(1.2)'],
['golden-meadow','Golden Meadow','meadow','#edaf3c50','sepia(.18)'],
['starlit-meadow','Starlit Meadow','meadow','#1823669c','brightness(.7)'],
['mountain','Mountain Lake','mountain','#25987900','none'],
['emerald-lake','Emerald Reflections','mountain','#07956335','saturate(1.25)'],
['autumn','Autumn Escape','mountain','#e08b303a','sepia(.15) saturate(1.2)'],
['misty-forest','Misty Forest','forest','#b4dde938','brightness(1.05) saturate(.75)'],
['rainy-forest','Rainy Retreat','forest','#143c7066','brightness(.8) saturate(.8)'],
['sakura-dawn','Sakura Dawn','city','#ffbfd735','brightness(1.05) saturate(.9)']
].map(([id,name,image,tint,filter])=>({id,name,image,tint,filter}));
catalog.push(...extraBackgrounds);
export const themeIds=catalog.map(t=>t.id);
export function getTheme(id){return catalog.find(t=>t.id===id)||catalog[0];}
export const sets=collectionDefs.map(c=>c.id);
export const setNames=Object.fromEntries(collectionDefs.map(c=>[c.id,c.name]));
