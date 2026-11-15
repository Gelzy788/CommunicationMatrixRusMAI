import { Link } from 'react-router'

/**
 * Мобилка: разделитель по ширине контента.
 * ПК (lg): одна строка высотой --footer-height
 */
export function Footer() {
  return (
    <footer className="page-container">
      <div className="flex flex-col-reverse gap-4 border-t border-border pt-4 pb-6 lg:h-(--footer-height) lg:flex-row lg:items-center lg:justify-between lg:border-t-0 lg:py-0">
        <p className="text-small text-muted-foreground lg:text-caption">
          &copy; Слова · Заботимся о понимании
        </p>
        <Link
          to="/privacy"
          className="flex min-h-11 items-center self-start text-label link lg:min-h-0 lg:self-auto lg:text-caption lg:font-normal lg:text-muted-foreground lg:hover:text-foreground"
        >
          Политика конфиденциальности
        </Link>
      </div>
    </footer>
  )
}
