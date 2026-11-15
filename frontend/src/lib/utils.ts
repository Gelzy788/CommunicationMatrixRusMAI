import { createCn } from 'cn/config'

// Наши классы типографики из index.css (text-h1, text-label…). Без этого cn
// принимает их за цвет текста и выкидывает рядом с text-foreground и т.п.
export const cn = createCn({
  extend: {
    classGroups: {
      'font-size': [
        { text: ['hero', 'h1', 'h2', 'h3', 'h4', 'body', 'label', 'small', 'caption', 'overline'] },
      ],
    },
  },
})
